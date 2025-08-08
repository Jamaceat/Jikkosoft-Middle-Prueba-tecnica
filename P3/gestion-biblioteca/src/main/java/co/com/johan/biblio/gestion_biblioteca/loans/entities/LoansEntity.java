package co.com.johan.biblio.gestion_biblioteca.loans.entities;

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
@Table(name = "prestamos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class LoansEntity {

    @Id    @Column(name = "id")
    private Long id;

    @Column(name = "libro_id")
    private Long bookId;

    @Column(name = "miembro_id")
    private Long memberId;

}
