package co.com.johan.biblio.gestion_biblioteca.books.services;

import co.com.johan.biblio.gestion_biblioteca.books.dtos.request.RegisterBranchR;
import co.com.johan.biblio.gestion_biblioteca.books.entities.BranchEntity;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;

public interface BranchService {
    
    BranchEntity registerBranch(RegisterBranchR registerBranchR);

    PaginationSimplified<BranchEntity> getAllBranches(Long pageNumber,Long pageSize);


}
