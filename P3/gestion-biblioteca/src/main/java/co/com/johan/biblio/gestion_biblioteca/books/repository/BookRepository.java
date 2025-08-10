package co.com.johan.biblio.gestion_biblioteca.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.johan.biblio.gestion_biblioteca.books.entities.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    
}
