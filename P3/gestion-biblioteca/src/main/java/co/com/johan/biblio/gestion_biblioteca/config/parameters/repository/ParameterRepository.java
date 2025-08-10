package co.com.johan.biblio.gestion_biblioteca.config.parameters.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.johan.biblio.gestion_biblioteca.config.parameters.model.ParameterEntity;

public interface ParameterRepository extends JpaRepository<ParameterEntity, Long> {
     
}
