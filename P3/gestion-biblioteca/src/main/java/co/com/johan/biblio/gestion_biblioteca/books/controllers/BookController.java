package co.com.johan.biblio.gestion_biblioteca.books.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.johan.biblio.gestion_biblioteca.dtos.response.GeneralResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/books",produces = {MediaType.APPLICATION_JSON_VALUE})
public class BookController {

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getMethodName(@RequestParam String param) {
        
        return ResponseEntity.ok(null);
    }
    
    
    
}
