package co.com.johan.biblio.gestion_biblioteca.books.entities;

import java.time.LocalDate;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "libros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BookEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "titulo")
    private String title;

    @Column(name = "autor")
    private String author;

    @Column(name = "ano_publicacion")
    private Long releaseYear;

    @Column(name = "genero")
    private String genre;

    @Column(name="cantidad_total")
    private Long totalAmount;

    @Column(name="cantidad_disponible")
    private Long availableAmount;

    @Column(name="fecha_registro")
    @CreatedDate
    private LocalDate registerDate;

    @Column(name ="fecha_actualizacion")
    @LastModifiedDate
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", insertable = true, updatable = true)
    @JsonManagedReference
    private BranchEntity branch;

    
}
