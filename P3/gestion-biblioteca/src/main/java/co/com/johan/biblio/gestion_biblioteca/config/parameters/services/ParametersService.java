package co.com.johan.biblio.gestion_biblioteca.config.parameters.services;

import co.com.johan.biblio.gestion_biblioteca.config.parameters.model.ParameterEntity;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;

public interface ParametersService {
    
    ParameterEntity getParameterByName(String request);

    PaginationSimplified<ParameterEntity> getAllParameters(Long pageNumber, Long pageSize );


}
