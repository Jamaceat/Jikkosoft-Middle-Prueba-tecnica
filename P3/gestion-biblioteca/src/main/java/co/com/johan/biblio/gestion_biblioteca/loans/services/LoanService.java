package co.com.johan.biblio.gestion_biblioteca.loans.services;

import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;
import co.com.johan.biblio.gestion_biblioteca.loans.entities.LoansEntity;

public interface LoanService {
    

    PaginationSimplified<LoansEntity> getAllLoans(Long pageNumber,Long pageSize);


}
