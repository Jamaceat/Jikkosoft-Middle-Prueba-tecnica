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

import co.com.johan.biblio.gestion_biblioteca.books.dtos.request.RegisterBranchR;
import co.com.johan.biblio.gestion_biblioteca.books.entities.BranchEntity;
import co.com.johan.biblio.gestion_biblioteca.books.services.BranchService;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.GeneralResponse;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.Response;

@RestController
@RequestMapping(path = "/branch", produces = { MediaType.APPLICATION_JSON_VALUE })
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping("/register")
    public ResponseEntity<GeneralResponse> registerBranch(@RequestBody RegisterBranchR registerBranchR) {

        BranchEntity branch = branchService.registerBranch(registerBranchR);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response<>(branch, HttpStatus.CREATED.toString(), "Sucursal creada exitosamente"));
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse> getMethodName(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "1") Long pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Long pageSize) {
       PaginationSimplified<BranchEntity> branches= branchService.getAllBranches(pageNumber, pageSize);

       return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(branches, HttpStatus.OK.toString(), "Sucursales obtenidas exitosamente"));
    }

}
