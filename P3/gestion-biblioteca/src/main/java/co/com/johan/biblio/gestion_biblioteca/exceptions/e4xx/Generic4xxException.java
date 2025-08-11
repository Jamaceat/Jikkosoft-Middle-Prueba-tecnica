package co.com.johan.biblio.gestion_biblioteca.exceptions.e4xx;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
@Setter
public class Generic4xxException extends RuntimeException {
    public Generic4xxException(String message) {
        super(message);
    }
}
