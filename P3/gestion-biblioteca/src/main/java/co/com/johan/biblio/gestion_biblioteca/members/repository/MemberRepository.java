package co.com.johan.biblio.gestion_biblioteca.members.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.johan.biblio.gestion_biblioteca.members.entities.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    


}
