package co.com.johan.biblio.gestion_biblioteca.loans.entities;

import java.time.LocalDate;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.com.johan.biblio.gestion_biblioteca.loans.dtos.LoanStateEnum;
import co.com.johan.biblio.gestion_biblioteca.utils.converters.LoanStateEnumType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class LoanEntity {

    @Id    
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libro_id")
    private Long bookId;

    @Column(name = "miembro_id")
    private Long memberId;

    @Column(name = "fecha_prestamo")
    private LocalDate loanDate;

    @Column(name = "fecha_devolucion_esperada")
    private LocalDate expectedReturnDate;

    @Column(name = "fecha_devolucion_real")
    private LocalDate actualReturnDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado",columnDefinition = "estado_prestamo")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private LoanStateEnum state;
    

}
