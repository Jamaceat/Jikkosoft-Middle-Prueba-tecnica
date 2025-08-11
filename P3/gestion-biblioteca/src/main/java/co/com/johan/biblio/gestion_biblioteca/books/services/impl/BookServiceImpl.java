package co.com.johan.biblio.gestion_biblioteca.books.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.johan.biblio.gestion_biblioteca.books.dtos.request.RegisterBookR;
import co.com.johan.biblio.gestion_biblioteca.books.entities.BookEntity;
import co.com.johan.biblio.gestion_biblioteca.books.entities.BranchEntity;
import co.com.johan.biblio.gestion_biblioteca.books.repository.BookRepository;
import co.com.johan.biblio.gestion_biblioteca.books.repository.BranchRepository;
import co.com.johan.biblio.gestion_biblioteca.books.services.BookService;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;
import co.com.johan.biblio.gestion_biblioteca.utils.mappers.BookMapper;
import co.com.johan.biblio.gestion_biblioteca.utils.mappers.PaginationMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    
    private final BookRepository bookRepository;

    
    private final BranchRepository branchRepository;

    
    private final PaginationMapper paginationMapper;

    
    private final BookMapper bookMapper;


    @SuppressWarnings("unchecked")
    @Override
    public PaginationSimplified<BookEntity> getAllBooks(Long pageNumber, Long pageSize) {
        Pageable pageable = PageRequest.of(pageNumber.intValue()-1, pageSize.intValue());
        Page<BookEntity> books = bookRepository.findAll(pageable);
        PaginationSimplified<BookEntity> pagination = paginationMapper.pageToPaginationSimplified(books);
        return pagination;
    }



    @Override
    public BookEntity registerBook(RegisterBookR registerBookR) {

        BranchEntity branch = branchRepository.findById(registerBookR.branchId()).orElseThrow();
        BookEntity book = bookMapper.bookRegisterToEntity(registerBookR);
        book.setBranch(branch);
        book = bookRepository.save(book);
        return book;
    }
    

}
