package co.com.johan.biblio.gestion_biblioteca.loans.services.impl;

import org.springframework.stereotype.Service;

import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;
import co.com.johan.biblio.gestion_biblioteca.loans.entities.LoansEntity;
import co.com.johan.biblio.gestion_biblioteca.loans.services.LoanService;

@Service
public class LoanServiceImpl  implements LoanService {

    @Override
    public PaginationSimplified<LoansEntity> getAllLoans(Long pageNumber, Long pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllLoans'");
    }
    
}
