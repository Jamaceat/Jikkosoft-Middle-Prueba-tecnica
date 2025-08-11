package co.com.johan.biblio.gestion_biblioteca.utils.mappers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.com.johan.biblio.gestion_biblioteca.books.entities.BookEntity;
import co.com.johan.biblio.gestion_biblioteca.loans.dtos.LoanStateEnum;
import co.com.johan.biblio.gestion_biblioteca.loans.entities.LoanEntity;

@Mapper(componentModel = "spring")
public abstract class LoanMapper {
    

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "loanDate" ,ignore = true)
    @Mapping(target = "expectedReturnDate",ignore = true)
    @Mapping(target = "actualReturnDate",ignore = true)
    @Mapping(target = "state",ignore = true)
    @Mapping(target = "memberId",source = "memberId")
    @Mapping(target = "bookId",source = "bookId")
    @Mapping(target = "bookEntity", source ="bookEntity" )
    public abstract LoanEntity registerLoanToEntity(Long memberId,Long bookId, Long daysUntilReturn,BookEntity bookEntity);

    @AfterMapping
    protected void calculation(@MappingTarget LoanEntity loanEntity,Long daysUntilReturn){
        loanEntity.setLoanDate(LocalDate.now());
        loanEntity.setState(LoanStateEnum.TAKEN);
        loanEntity.setExpectedReturnDate(loanEntity.getLoanDate().plus(daysUntilReturn, ChronoUnit.DAYS));
    }   
    

}
