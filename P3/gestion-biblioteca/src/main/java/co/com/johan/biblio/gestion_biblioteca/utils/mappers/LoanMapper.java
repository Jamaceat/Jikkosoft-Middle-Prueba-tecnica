package co.com.johan.biblio.gestion_biblioteca.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.johan.biblio.gestion_biblioteca.loans.dtos.request.RegisterLoansR;
import co.com.johan.biblio.gestion_biblioteca.loans.entities.LoansEntity;

@Mapper(componentModel = "spring")
public abstract class LoanMapper {
    

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "loanDate",ignore = true)
    @Mapping(target = "expectedReturnDate",ignore = true)
    @Mapping(target = "actualReturnDate",ignore = true)
    @Mapping(target = "state",ignore = true)
    public abstract LoansEntity registerLoanToEntity(RegisterLoansR registerLoansR);
    


}
