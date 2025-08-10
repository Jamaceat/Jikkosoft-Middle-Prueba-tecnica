package co.com.johan.biblio.gestion_biblioteca.books.services;

import org.springframework.data.domain.Page;

import co.com.johan.biblio.gestion_biblioteca.books.dtos.request.RegisterBranchR;
import co.com.johan.biblio.gestion_biblioteca.books.entities.BranchEntity;

public interface BranchService {
    
    BranchEntity registerBranch(RegisterBranchR registerBranchR);

    Page<BranchEntity> getAllBranches(Long pageNumber,Long pageSize);


}
