package co.com.johan.biblio.gestion_biblioteca.loans.controllers;

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

import co.com.johan.biblio.gestion_biblioteca.dtos.response.GeneralResponse;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;
import co.com.johan.biblio.gestion_biblioteca.loans.entities.LoansEntity;
import co.com.johan.biblio.gestion_biblioteca.loans.services.LoansService;
import co.com.johan.biblio.gestion_biblioteca.utils.response.ResponseBuilder;




@RestController
@RequestMapping(value = "/loans", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoansController {

@Autowired
private LoansService loansService;

    
@GetMapping("/all")
public ResponseEntity<GeneralResponse> getAllLoans(@RequestParam(name = "pageNumber", required = false, defaultValue = "1") Long pageNumber,
        @RequestParam(name = "pageSize", required = false, defaultValue = "10") Long pageSize) {

            PaginationSimplified<LoansEntity> loans = loansService.getAllLoans(pageNumber, pageSize);
            return ResponseBuilder.of(loans, HttpStatus.OK , "Prestamos obtenidos exitosamente");
}


@PostMapping("/register")
public String postMethodName(@RequestBody String entity) {
    //TODO: process POST request
    
    return entity;
}



}
