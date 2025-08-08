package co.com.johan.biblio.gestion_biblioteca.members.entities;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
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



}
