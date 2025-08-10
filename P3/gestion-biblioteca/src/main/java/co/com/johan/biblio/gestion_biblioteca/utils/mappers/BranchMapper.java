package co.com.johan.biblio.gestion_biblioteca.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.johan.biblio.gestion_biblioteca.books.dtos.request.RegisterBranchR;
import co.com.johan.biblio.gestion_biblioteca.books.entities.BranchEntity;

@Mapper(componentModel = "spring")
public abstract class BranchMapper {

    @Mapping(target = "id",ignore = true)
    public abstract BranchEntity branchRegisterToEntity(RegisterBranchR registerBranchR);
    
}
