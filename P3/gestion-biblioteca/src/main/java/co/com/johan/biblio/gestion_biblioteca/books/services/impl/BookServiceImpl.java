package co.com.johan.biblio.gestion_biblioteca.books.services.impl;

import org.springframework.stereotype.Service;

import co.com.johan.biblio.gestion_biblioteca.books.entities.BookEntity;
import co.com.johan.biblio.gestion_biblioteca.books.services.BookService;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;

@Service
public class BookServiceImpl implements BookService{

    @Override
    public PaginationSimplified<BookEntity> getAllBooks(Long pageNumber, Long pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllBooks'");
    }
    
}
