package co.com.johan.biblio.gestion_biblioteca.members.entities;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "miembros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name="nombre")
    String name;
    
    @Column(name="email")
    String email;
    
    @Column(name="telefono")
    String phone;
    
    @Column(name="hash_contrasenia")
    String password;
    
    @Column(name="fecha_registro")
    @CreatedDate
    private LocalDate signUpDate;

    @Column(name ="fecha_actualizacion")
    @LastModifiedDate
    private LocalDate updateDate;

    @ManyToMany
    @JoinTable(name = "roles_miembros"
    ,joinColumns = @JoinColumn(name="miembro_id"),
    inverseJoinColumns = @JoinColumn(name="rol_id")
    )
    @JsonManagedReference
    List<RoleEntity> roles;

    

}
