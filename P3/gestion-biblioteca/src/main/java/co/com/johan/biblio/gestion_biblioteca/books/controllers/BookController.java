package co.com.johan.biblio.gestion_biblioteca.books.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.johan.biblio.gestion_biblioteca.books.dtos.request.RegisterBookR;
import co.com.johan.biblio.gestion_biblioteca.books.entities.BookEntity;
import co.com.johan.biblio.gestion_biblioteca.books.services.BookService;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.GeneralResponse;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;
import co.com.johan.biblio.gestion_biblioteca.utils.response.ResponseBuilder;




@RestController
@RequestMapping(path = "/books",produces = {MediaType.APPLICATION_JSON_VALUE})
public class BookController {

    @Autowired
    BookService bookService;



    @GetMapping("/all")
    public ResponseEntity<GeneralResponse> getAllBooks(@RequestParam(name = "pageNumber", required = false, defaultValue = "1") Long pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Long pageSize) {
        PaginationSimplified<BookEntity> books = bookService.getAllBooks(pageNumber, pageSize);

        return ResponseBuilder.of(books,HttpStatus.OK, "Libros obtenidos exitosamente");
    }
    
    @PostMapping("/register")
    public ResponseEntity<GeneralResponse> registerBook(@RequestBody RegisterBookR book) {
        BookEntity result=bookService.registerBook(book);
        return ResponseBuilder.of(result, HttpStatus.CREATED, "Libro registrado exitosamente");
        
    }
    
    
}
