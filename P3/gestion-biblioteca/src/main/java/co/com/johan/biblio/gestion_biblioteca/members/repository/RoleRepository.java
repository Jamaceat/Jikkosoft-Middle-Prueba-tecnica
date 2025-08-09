package co.com.johan.biblio.gestion_biblioteca.members.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.johan.biblio.gestion_biblioteca.members.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query("SELECT r FROM RoleEntity r WHERE r.rolName IN :roles")
    List<RoleEntity> findByRolNameIn(List<String> roles);
}
