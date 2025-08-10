package co.com.johan.biblio.gestion_biblioteca.loans.services;

import java.util.List;

import org.apache.coyote.BadRequestException;

import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;
import co.com.johan.biblio.gestion_biblioteca.loans.dtos.request.RegisterLoansR;
import co.com.johan.biblio.gestion_biblioteca.loans.entities.LoanEntity;

public interface LoansService {
    

    PaginationSimplified<LoanEntity> getAllLoans(Long pageNumber,Long pageSize);

    PaginationSimplified<LoanEntity> getAllLoansByUser(Long pageNumber,Long pageSize);

    List<LoanEntity> registerLoans(RegisterLoansR loans) throws BadRequestException;

    List<LoanEntity> returnLoans(RegisterLoansR loans);




}
