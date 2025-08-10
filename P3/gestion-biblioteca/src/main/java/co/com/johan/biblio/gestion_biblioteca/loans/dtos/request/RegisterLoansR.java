package co.com.johan.biblio.gestion_biblioteca.loans.dtos.request;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;

@Validated
public record RegisterLoansR(@NotEmpty List<Long> booksId) {
    
}
