package co.com.johan.biblio.gestion_biblioteca.members.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolesEntity {

    @Id    
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_rol")
    private String rolName;

    @Column(name = "descripcion_rol")
    private String description;
}