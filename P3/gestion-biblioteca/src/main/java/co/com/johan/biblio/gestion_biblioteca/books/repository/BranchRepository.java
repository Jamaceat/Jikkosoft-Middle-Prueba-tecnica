package co.com.johan.biblio.gestion_biblioteca.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.johan.biblio.gestion_biblioteca.books.entities.BranchEntity;

public interface BranchRepository extends JpaRepository<BranchEntity, Long> {
    
}
