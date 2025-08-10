package co.com.johan.biblio.gestion_biblioteca.books.services;

import co.com.johan.biblio.gestion_biblioteca.books.dtos.request.RegisterBookR;
import co.com.johan.biblio.gestion_biblioteca.books.entities.BookEntity;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;

public interface BookService {
    
    PaginationSimplified<BookEntity> getAllBooks(Long pageNumber,Long pageSize);

    BookEntity registerBook(RegisterBookR registerBookR);

}
