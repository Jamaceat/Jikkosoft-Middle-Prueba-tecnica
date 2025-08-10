package co.com.johan.biblio.gestion_biblioteca.utils.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.com.johan.biblio.gestion_biblioteca.dtos.response.ErrorResponse;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.GeneralResponse;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.Response;

public class ResponseBuilder {
    

    public static <T> ResponseEntity<GeneralResponse> of(T result,HttpStatus status,String message){

        return ResponseEntity.status(status).body(new Response<T>(result, status.toString(), message));
    }

    public static ResponseEntity<GeneralResponse> ofError(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new ErrorResponse(message));
    }

}
