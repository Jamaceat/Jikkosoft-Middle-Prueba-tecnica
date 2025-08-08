package co.com.johan.biblio.gestion_biblioteca.books.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.johan.biblio.gestion_biblioteca.dtos.response.GeneralResponse;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.Response;



@RestController
@RequestMapping(path = "/branch",produces = {MediaType.APPLICATION_JSON_VALUE})
public class BranchController {


    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getBranchById(@PathVariable("id") Long id) {
        Response<Long> result=new Response<>(id,HttpStatus.OK.toString(),null);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
    
}
