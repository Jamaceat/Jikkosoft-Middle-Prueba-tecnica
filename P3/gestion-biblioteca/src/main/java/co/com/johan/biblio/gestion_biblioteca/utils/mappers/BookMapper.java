package co.com.johan.biblio.gestion_biblioteca.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.johan.biblio.gestion_biblioteca.books.dtos.request.RegisterBookR;
import co.com.johan.biblio.gestion_biblioteca.books.entities.BookEntity;

@Mapper(componentModel = "spring")
public abstract class BookMapper {


    @Mapping(target = "id",ignore = true)
    @Mapping(target = "registerDate",ignore = true)
    @Mapping(target = "updateDate",ignore = true)
    @Mapping(target = "branch",ignore = true)
    @Mapping(target = "availableAmount",source = "totalAmount")
    public abstract BookEntity bookRegisterToEntity(RegisterBookR registerBookR);

    
}
