package co.com.johan.biblio.gestion_biblioteca.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import co.com.johan.biblio.gestion_biblioteca.books.entities.BranchEntity;

public interface BranchRepository extends JpaRepository<BranchEntity, Long>, JpaSpecificationExecutor<BranchEntity> {

}
