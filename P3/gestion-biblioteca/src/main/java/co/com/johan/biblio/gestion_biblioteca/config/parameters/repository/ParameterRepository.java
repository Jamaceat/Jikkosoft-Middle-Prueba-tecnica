package co.com.johan.biblio.gestion_biblioteca.config.parameters.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.johan.biblio.gestion_biblioteca.config.parameters.model.ParameterEntity;

public interface ParameterRepository extends JpaRepository<ParameterEntity, Long> {

    Optional<ParameterEntity> findByName(String name);
     
}
