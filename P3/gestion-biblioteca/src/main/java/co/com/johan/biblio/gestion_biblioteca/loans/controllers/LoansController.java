package co.com.johan.biblio.gestion_biblioteca.loans.controllers;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.johan.biblio.gestion_biblioteca.dtos.response.GeneralResponse;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;
import co.com.johan.biblio.gestion_biblioteca.loans.dtos.request.RegisterLoansR;
import co.com.johan.biblio.gestion_biblioteca.loans.dtos.request.ReturnLoansR;
import co.com.johan.biblio.gestion_biblioteca.loans.entities.LoanEntity;
import co.com.johan.biblio.gestion_biblioteca.loans.services.LoansService;
import co.com.johan.biblio.gestion_biblioteca.utils.response.ResponseBuilder;
import lombok.RequiredArgsConstructor;





@RestController
@RequestMapping(value = "/loans", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class LoansController {


private final LoansService loansService;

    
@GetMapping("/all")
public ResponseEntity<GeneralResponse> getAllLoans(@RequestParam(name = "pageNumber", required = false, defaultValue = "1") Long pageNumber,
        @RequestParam(name = "pageSize", required = false, defaultValue = "10") Long pageSize) {

            PaginationSimplified<LoanEntity> loans = loansService.getAllLoans(pageNumber, pageSize);
            return ResponseBuilder.of(loans, HttpStatus.OK , "Prestamos obtenidos exitosamente");
}
@GetMapping("/member/all")
public ResponseEntity<GeneralResponse> getAllLoansByUser(@RequestParam(name = "pageNumber", required = false, defaultValue = "1") Long pageNumber,
        @RequestParam(name = "pageSize", required = false, defaultValue = "10") Long pageSize) {

            PaginationSimplified<LoanEntity> loans = loansService.getAllLoansByUser(pageNumber, pageSize);
            return ResponseBuilder.of(loans, HttpStatus.OK , "Prestamos obtenidos exitosamente");
}


@PostMapping("/register/list")
public ResponseEntity<GeneralResponse> postLoansByUser(@RequestBody RegisterLoansR loans) throws BadRequestException {
    
    List<LoanEntity> result = loansService.registerLoans(loans);
    return ResponseBuilder.of(result, HttpStatus.CREATED, "Prestamos registrados exitosamente" );
}


@PutMapping("/return/list")
public ResponseEntity<GeneralResponse> putMethodName( @RequestBody ReturnLoansR loans) throws BadRequestException {
    List<LoanEntity> result= loansService.returnLoans(loans);
    return ResponseBuilder.of(result,HttpStatus.NO_CONTENT, "Prestamos actualizados exitosamente");
    }
}
