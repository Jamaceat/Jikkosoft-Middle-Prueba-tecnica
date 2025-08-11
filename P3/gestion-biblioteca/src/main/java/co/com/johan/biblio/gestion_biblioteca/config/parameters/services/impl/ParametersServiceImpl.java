package co.com.johan.biblio.gestion_biblioteca.config.parameters.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.johan.biblio.gestion_biblioteca.config.parameters.model.ParameterEntity;
import co.com.johan.biblio.gestion_biblioteca.config.parameters.repository.ParameterRepository;
import co.com.johan.biblio.gestion_biblioteca.config.parameters.services.ParametersService;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;
import co.com.johan.biblio.gestion_biblioteca.utils.mappers.PaginationMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParametersServiceImpl implements ParametersService {

    
    private final ParameterRepository parameterRepository;


    private final PaginationMapper paginationMapper;


    @Override
    public ParameterEntity getParameterByName(String request) {
        
        return parameterRepository.findByName(request.toUpperCase()).orElseThrow();

    }

    @SuppressWarnings("unchecked")
    @Override
    public PaginationSimplified<ParameterEntity> getAllParameters(Long pageNumber, Long pageSize) {
        Pageable pageable= PageRequest.of(pageNumber.intValue()-1, pageSize.intValue());
        Page<ParameterEntity> page = parameterRepository.findAll(pageable);
        return paginationMapper.pageToPaginationSimplified(page);
    }
    
}
