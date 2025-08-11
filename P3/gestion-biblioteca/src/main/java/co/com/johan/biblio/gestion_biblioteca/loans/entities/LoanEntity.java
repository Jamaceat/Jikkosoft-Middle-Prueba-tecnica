package co.com.johan.biblio.gestion_biblioteca.loans.entities;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.com.johan.biblio.gestion_biblioteca.books.entities.BookEntity;
import co.com.johan.biblio.gestion_biblioteca.loans.dtos.LoanStateEnum;
import co.com.johan.biblio.gestion_biblioteca.members.entities.MemberEntity;
import co.com.johan.biblio.gestion_biblioteca.utils.converters.LoanStateConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

    @Convert(converter = LoanStateConverter.class)
    @Column(name = "estado",columnDefinition = "estado_prestamo")
    @ColumnTransformer(write = "?::estado_prestamo")
    private LoanStateEnum state;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "miembro_id",insertable = false, updatable = false)
    private MemberEntity member;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id",insertable = false, updatable = false)
    private BookEntity bookEntity;


}
