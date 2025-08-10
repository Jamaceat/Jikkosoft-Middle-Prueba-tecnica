package co.com.johan.biblio.gestion_biblioteca.loans.services;

import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;
import co.com.johan.biblio.gestion_biblioteca.loans.dtos.request.RegisterLoansR;
import co.com.johan.biblio.gestion_biblioteca.loans.entities.LoansEntity;

public interface LoansService {
    

    PaginationSimplified<LoansEntity> getAllLoans(Long pageNumber,Long pageSize);

    LoansEntity registerLoan(RegisterLoansR registerLoansR);

}
