package co.com.johan.biblio.gestion_biblioteca.loans.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import co.com.johan.biblio.gestion_biblioteca.loans.entities.LoanEntity;

public interface LoanRepository extends JpaRepository<LoanEntity, Long>{
    
    Page<LoanEntity> findAllByMemberId(Long memberId,Pageable pageable);
}
