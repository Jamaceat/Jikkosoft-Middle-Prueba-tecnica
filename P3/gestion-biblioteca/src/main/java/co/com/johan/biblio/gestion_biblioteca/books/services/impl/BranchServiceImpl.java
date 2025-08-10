package co.com.johan.biblio.gestion_biblioteca.books.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.com.johan.biblio.gestion_biblioteca.books.dtos.request.RegisterBranchR;
import co.com.johan.biblio.gestion_biblioteca.books.entities.BranchEntity;
import co.com.johan.biblio.gestion_biblioteca.books.repository.BranchRepository;
import co.com.johan.biblio.gestion_biblioteca.books.services.BranchService;
import co.com.johan.biblio.gestion_biblioteca.utils.mappers.BranchMapper;

@Service
public class BranchServiceImpl implements BranchService{

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    BranchMapper branchMapper;


    @Override
    public BranchEntity registerBranch(RegisterBranchR registerBranchR) {

            BranchEntity branch=branchMapper.branchRegisterToEntity(registerBranchR);
            branch=branchRepository.save(branch);
            return branch;
    }


    @Override
    public Page<BranchEntity> getAllBranches(Long pageNumber, Long pageSize) {
       Pageable page=PageRequest.of(pageNumber.intValue()-1,pageSize.intValue(),Sort.by("name").ascending());
        return branchRepository.findAll(page);

    }
    
}
