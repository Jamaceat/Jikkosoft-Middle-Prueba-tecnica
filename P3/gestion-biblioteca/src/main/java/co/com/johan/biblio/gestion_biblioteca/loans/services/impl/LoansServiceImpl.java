package co.com.johan.biblio.gestion_biblioteca.loans.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import co.com.johan.biblio.gestion_biblioteca.books.entities.BookEntity;
import co.com.johan.biblio.gestion_biblioteca.books.repository.BookRepository;
import co.com.johan.biblio.gestion_biblioteca.config.parameters.repository.ParameterRepository;
import co.com.johan.biblio.gestion_biblioteca.constants.SecurityConstants;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;
import co.com.johan.biblio.gestion_biblioteca.exceptions.e4xx.ConflictException;
import co.com.johan.biblio.gestion_biblioteca.loans.dtos.LoanStateEnum;
import co.com.johan.biblio.gestion_biblioteca.loans.dtos.request.RegisterLoansR;
import co.com.johan.biblio.gestion_biblioteca.loans.dtos.request.ReturnLoansR;
import co.com.johan.biblio.gestion_biblioteca.loans.entities.LoanEntity;
import co.com.johan.biblio.gestion_biblioteca.loans.repository.LoanRepository;
import co.com.johan.biblio.gestion_biblioteca.loans.services.LoansService;
import co.com.johan.biblio.gestion_biblioteca.members.entities.MemberEntity;
import co.com.johan.biblio.gestion_biblioteca.members.repository.MemberRepository;
import co.com.johan.biblio.gestion_biblioteca.utils.mappers.LoanMapper;
import co.com.johan.biblio.gestion_biblioteca.utils.mappers.PaginationMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoansServiceImpl implements LoansService {

        @Qualifier("virtualThreadExecutor")
        private final ExecutorService virtualThreadExecutor;

        private final MemberRepository memberRepository;

        private final LoanRepository loanRepository;

        private final BookRepository bookRepository;

        private final ParameterRepository parameterRepository;

        private final PaginationMapper paginationMapper;

        private final LoanMapper loanMapper;


        private static String PARAMETER_PRESTAMO_DIAS_HASTA_RETORNO = "PRESTAMO_DIAS_HASTA_RETORNO";

        @SuppressWarnings("unchecked")
        @Override
        public PaginationSimplified<LoanEntity> getAllLoans(Long pageNumber, Long pageSize) {

                Pageable pageable = PageRequest.of(pageNumber.intValue() - 1, pageSize.intValue());

                Page<LoanEntity> loans = loanRepository.findAll(pageable);

                return paginationMapper.pageToPaginationSimplified(loans);
        }

        @SuppressWarnings("unchecked")
        @Override
        public PaginationSimplified<LoanEntity> getAllLoansByUser(Long pageNumber, Long pageSize) {

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String email = authentication.getPrincipal().toString();
                log.info("email: {}", email);

                Long userId = memberRepository.findByEmail(email).orElseThrow().getId();

                Pageable pageable = PageRequest.of(pageNumber.intValue() - 1, pageSize.intValue());

                Page<LoanEntity> loans = loanRepository.findAllByMemberId(userId, pageable);

                return paginationMapper.pageToPaginationSimplified(loans);
        }

        @Override
        @Transactional(rollbackOn = Exception.class)
        public List<LoanEntity> registerLoans(RegisterLoansR loans) throws BadRequestException {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String email = authentication.getPrincipal().toString();
                log.info("email: {}", email);

                Long userId = memberRepository.findByEmail(email).orElseThrow().getId();

                List<BookEntity> booksRepository = bookRepository.findAllById(loans.booksId());

                List<BookEntity> booksRelatedToLoans = loans
                                .booksId().stream().map(bookId -> booksRepository.stream()
                                                .filter(book -> book.getId() == bookId).findFirst().orElseThrow())
                                .toList();

                List<BookEntity> books = booksRepository.stream().filter(book -> book.getAvailableAmount() > 0)
                                .collect(Collectors.toList());

                if (books.stream().map(BookEntity::getId).distinct().count() != loans.booksId().stream().distinct().count()) {
                        String remainingBooks = loans.booksId().stream()
                                        .filter(bookId -> books.stream()
                                                        .allMatch(currentBook -> currentBook.getId() != bookId))
                                        .map(String::valueOf)
                                        .collect(Collectors.joining(","));
                        throw new BadRequestException(
                                        "hay uno o varios libros que no estan disponibles o no existen con los Ids: "
                                                        + remainingBooks);

                }

                Long daysUntilReturn = Long.parseLong(
                                parameterRepository.findByName(PARAMETER_PRESTAMO_DIAS_HASTA_RETORNO).orElseThrow()
                                                .getValue());

                List<CompletableFuture<LoanEntity>> mapFutures = booksRelatedToLoans.stream()
                                .map(book -> CompletableFuture
                                                .supplyAsync(() -> loanMapper.registerLoanToEntity(userId, book.getId(),
                                                                daysUntilReturn, book),
                                                                virtualThreadExecutor))
                                .collect(Collectors.toList());

                CompletableFuture.allOf(
                                mapFutures.toArray(new CompletableFuture[0])).join();

                List<LoanEntity> loansToRegister = mapFutures.stream()
                                .map(CompletableFuture::join)
                                .collect(Collectors.toList());

                List<LoanEntity> result = loanRepository.saveAll(loansToRegister);

                List<BookEntity> updateBooks = recalculateBooksLoan(
                                result.stream().map(loan -> loan.getBookId()).distinct().toList(),
                                result.stream().map(loan -> loan.getBookEntity()).toList());

                bookRepository.saveAll(updateBooks);

                return result;
        }

        @Override
        @Transactional(rollbackOn = Exception.class)
        public List<LoanEntity> returnLoans(ReturnLoansR loans) throws BadRequestException {

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String email = authentication.getPrincipal().toString();
                log.info("email: {}", email);

                MemberEntity memberId = memberRepository.findByEmail(email).orElseThrow();
                List<LoanEntity> memberLoans = memberId.getLoans();

                log.info("member loanIds {}", memberLoans.stream().map(LoanEntity::getBookId).map(String::valueOf)
                                .collect(Collectors.joining(",")));

                List<LoanEntity> validLoans = memberLoans.stream()
                                .filter(loan -> loans.LoanIds().stream()
                                                .anyMatch(compareLoan -> loan.getId().equals(compareLoan)))
                                .toList();

                if(validLoans.stream().allMatch(loan -> loan.getState().equals(LoanStateEnum.RETURNED)))throw new ConflictException("Existen prestamos que ya estan devueltos o no existen en la peticion") ;

                if (validLoans.stream().map(loan -> loan.getId()).distinct().toList().size() != loans.LoanIds()
                                .size()) {
                        String invalidLoans = loans.LoanIds().stream()
                                        .filter(loan -> memberLoans.stream()
                                                        .allMatch(compareLoan -> loan != compareLoan.getId()))
                                        .map(String::valueOf).collect(Collectors.joining(","));
                        throw new BadRequestException("Invalid LoansIds: " + invalidLoans);
                }

                // define la fecha actual de devolucion
                validLoans.parallelStream().forEach(validLoan -> validLoan.setActualReturnDate(LocalDate.now()));
                validLoans.parallelStream()
                                .forEach(validLoan -> validLoan.setState(
                                                validLoan.getExpectedReturnDate().isBefore(
                                                                validLoan.getActualReturnDate()) ? LoanStateEnum.LATE
                                                                                : LoanStateEnum.RETURNED));

                // guarda el estado de los prestamos
                List<LoanEntity> result = loanRepository.saveAll(validLoans);

                // recalcula los libros disponibles
                List<BookEntity> bookRecalculation = recalculateBooksReturn(
                                result.stream().map(loan -> loan.getBookId()).distinct().toList(),
                                result.stream().map(loan -> loan.getBookEntity()).toList());

                bookRepository.saveAll(bookRecalculation);

                return result;
        }

        private List<BookEntity> recalculateBooksReturn(List<Long> distinctBookIds, List<BookEntity> books)
                        throws BadRequestException {

                List<BookEntity> distinctBooks = distinctBookIds.stream()
                                .map(bookId -> books.stream().filter(book -> book.getId() == bookId).findFirst()
                                                .orElseThrow())
                                .toList();

                distinctBooks.forEach(uniqueBook -> uniqueBook.setAvailableAmount(uniqueBook.getAvailableAmount()
                                + books.stream().filter(book -> book.getId() == uniqueBook.getId()).count()));

                if (distinctBooks.stream().anyMatch(book -> book.getAvailableAmount() > book.getTotalAmount())) {

                        String errorBooks = distinctBooks.stream()
                                        .filter(book -> book.getAvailableAmount() > book.getTotalAmount())
                                        .map(book -> String.format("(%d;%d)",
                                                        book.getAvailableAmount() - book.getTotalAmount(),
                                                        book.getId()))
                                        .collect(Collectors.joining(","));

                        throw new BadRequestException(
                                        "la cantidad disponible supera la del total unidades de libros con (numeroSobrantes:id) :"
                                                        + errorBooks);

                }

                return distinctBooks;
        }

        private List<BookEntity> recalculateBooksLoan(List<Long> distinctBookIds, List<BookEntity> books)
                        throws BadRequestException {

                List<BookEntity> distinctBooks = distinctBookIds.stream()
                                .map(bookId -> books.stream().filter(book -> book.getId() == bookId).findFirst()
                                                .orElseThrow())
                                .toList();

                distinctBooks.forEach(uniqueBook -> uniqueBook.setAvailableAmount(uniqueBook.getAvailableAmount()
                                - books.stream().filter(book -> book.getId() == uniqueBook.getId()).count()));

                if (distinctBooks.stream().anyMatch(book -> book.getAvailableAmount() < 0)) {
                        String errorBooks = distinctBooks.stream().filter(book -> book.getAvailableAmount() < 0)
                                        .map(book -> String.format("(%d;%d)", Math.abs(book.getAvailableAmount()),
                                                        book.getId()))
                                        .collect(Collectors.joining(","));

                        throw new BadRequestException(
                                        "No hay suficiente unidades de libros con (numeroFaltantes:id) :" + errorBooks);
                }
                return distinctBooks;
        }

}
