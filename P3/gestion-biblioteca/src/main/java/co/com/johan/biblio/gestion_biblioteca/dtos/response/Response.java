package co.com.johan.biblio.gestion_biblioteca.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response<T> implements GeneralResponse{
    
    T result;
    String httpStatus;
    String message;

}
