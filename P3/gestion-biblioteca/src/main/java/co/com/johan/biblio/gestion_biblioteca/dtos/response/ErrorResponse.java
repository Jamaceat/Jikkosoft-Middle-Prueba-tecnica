package co.com.johan.biblio.gestion_biblioteca.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements GeneralResponse{
    
    private String errorMessage;

}
