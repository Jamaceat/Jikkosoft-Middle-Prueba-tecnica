package co.com.johan.biblio.gestion_biblioteca.config.parameters.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parametros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParameterEntity {
 
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_parametro")
    private String name;

    @Column(name = "valor_parametro")
    private String value;


}
