package co.com.johan.biblio.gestion_biblioteca.dtos.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaginationSimplified<T> {
    
    private List<T> content;

    private Integer pageNumber;
    private Integer pageSize;
    private Integer currentPageElements;
    private Long totalPages;
    private Long totalElements;
    private Long offsetElement;
    private Boolean currentPageIsEmpty;
    private Boolean currentPageIsFirst;
    private Boolean currentPageIslast;

}
