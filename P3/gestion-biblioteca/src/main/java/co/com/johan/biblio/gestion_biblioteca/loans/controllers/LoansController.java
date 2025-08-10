package co.com.johan.biblio.gestion_biblioteca.loans.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value = "/loans", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoansController {
    
@GetMapping("")
public String getMethodName( ) {
    return new String("hola ");
}

@PostMapping("/register")
public String postMethodName(@RequestBody String entity) {
    //TODO: process POST request
    
    return entity;
}



}
