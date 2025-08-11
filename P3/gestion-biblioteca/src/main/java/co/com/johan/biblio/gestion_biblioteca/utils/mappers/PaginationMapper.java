package co.com.johan.biblio.gestion_biblioteca.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;

@Mapper(componentModel = "spring")
public abstract class PaginationMapper {

    @SuppressWarnings("rawtypes")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "pageNumber", source = "page.pageable.pageNumber", qualifiedByName = "pageNumberCalculator")
    @Mapping(target = "pageSize", source = "page.pageable.pageSize")
    @Mapping(target = "currentPageElements", source = "page.numberOfElements")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "totalElements", source = "totalElements")
    @Mapping(target = "offsetElement", source = "page.pageable.offset")
    @Mapping(target = "currentPageIsEmpty", source = "empty")
    @Mapping(target = "currentPageIsFirst", source = "first")
    @Mapping(target = "currentPageIsLast", source = "page.last")
    public abstract PaginationSimplified pageToPaginationSimplified(Page page);

    @Named("pageNumberCalculator")
    protected Integer pageNumberCalculator(Integer pageNumber) {
        return pageNumber + 1;
    }
}
