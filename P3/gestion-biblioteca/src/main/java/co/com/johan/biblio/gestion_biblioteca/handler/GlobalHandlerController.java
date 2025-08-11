package co.com.johan.biblio.gestion_biblioteca.handler;

import java.nio.file.AccessDeniedException;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.com.johan.biblio.gestion_biblioteca.dtos.response.GeneralResponse;
import co.com.johan.biblio.gestion_biblioteca.utils.response.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalHandlerController {
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponse> handlerException( Exception exception){
        String specifidMessage = exception.getMessage()!=null?exception.getMessage():"";
        String generalMessage=exception.getStackTrace().length>0?exception.getStackTrace()[0].toString():"";       
        log.info("Especifico: {}",specifidMessage);  
        log.info("General: {}",generalMessage); 

        return ResponseBuilder.ofError(HttpStatus.INTERNAL_SERVER_ERROR, specifidMessage);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<GeneralResponse> handlerException( AccessDeniedException exception){
        String specifidMessage = exception.getMessage()!=null?exception.getMessage():"";
        String generalMessage=exception.getStackTrace().length>0?exception.getStackTrace()[0].toString():"";       
        log.info("Especifico: {}",specifidMessage);  
        log.info("General: {}",generalMessage); 

        return ResponseBuilder.ofError(HttpStatus.FORBIDDEN, specifidMessage);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GeneralResponse> handlerException( BadRequestException exception){
        String specifidMessage = exception.getMessage()!=null?exception.getMessage():"";
        String generalMessage=exception.getStackTrace().length>0?exception.getStackTrace()[0].toString():"";       
        log.info("Especifico: {}",specifidMessage);  
        log.info("General: {}",generalMessage); 

        return ResponseBuilder.ofError(HttpStatus.BAD_REQUEST, specifidMessage);
    }

    


}
