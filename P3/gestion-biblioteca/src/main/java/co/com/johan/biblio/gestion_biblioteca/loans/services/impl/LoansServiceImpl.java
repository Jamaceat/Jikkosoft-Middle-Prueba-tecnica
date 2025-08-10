package co.com.johan.biblio.gestion_biblioteca.loans.services.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
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
import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;
import co.com.johan.biblio.gestion_biblioteca.loans.dtos.request.RegisterLoansR;
import co.com.johan.biblio.gestion_biblioteca.loans.entities.LoanEntity;
import co.com.johan.biblio.gestion_biblioteca.loans.repository.LoanRepository;
import co.com.johan.biblio.gestion_biblioteca.loans.services.LoansService;
import co.com.johan.biblio.gestion_biblioteca.members.repository.MemberRepository;
import co.com.johan.biblio.gestion_biblioteca.utils.mappers.LoanMapper;
import co.com.johan.biblio.gestion_biblioteca.utils.mappers.PaginationMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoansServiceImpl implements LoansService {

    @Autowired
    @Qualifier("virtualThreadExecutor")
    private ExecutorService virtualThreadExecutor;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    ParameterRepository parameterRepository;

    @Autowired
    private PaginationMapper paginationMapper;

    @Autowired
    private LoanMapper loanMapper;

    private static String PARAMETER_PRESTAMO_DIAS_HASTA_RETORNO = "PRESTAMO_DIAS_HASTA_RETORNO";

    @Override
    public PaginationSimplified<LoanEntity> getAllLoans(Long pageNumber, Long pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllLoans'");
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

        List<BookEntity> books = bookRepository.findAllById(loans.booksId());

        books=books.stream().filter(book->book.getAvailableAmount()>0).collect(Collectors.toList());
        
        if (books.size() != loans.booksId().size())
            throw new BadRequestException("hay uno o varios libros que no existen");



        Long daysUntilReturn = Long.parseLong(
                parameterRepository.findByName(PARAMETER_PRESTAMO_DIAS_HASTA_RETORNO).orElseThrow().getValue());

        List<CompletableFuture<LoanEntity>> mapFutures = books.stream()
                .map(book -> CompletableFuture
                        .supplyAsync(() -> loanMapper.registerLoanToEntity(userId, book.getId(), daysUntilReturn), virtualThreadExecutor))
                .collect(Collectors.toList());

        CompletableFuture.allOf(
                mapFutures.toArray(new CompletableFuture[0])).join();

        List<LoanEntity> loansToRegister = mapFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        List<LoanEntity> result = loanRepository.saveAll(loansToRegister);

        books.forEach(book->book.setAvailableAmount(book.getAvailableAmount()-1));

        bookRepository.saveAll(books);

        return result;
    }

    @Override
    public List<LoanEntity> returnLoans(RegisterLoansR loans) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'returnLoans'");
    }

}
