package co.com.johan.biblio.gestion_biblioteca.config.parameters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.johan.biblio.gestion_biblioteca.config.parameters.model.ParameterEntity;
import co.com.johan.biblio.gestion_biblioteca.config.parameters.services.ParametersService;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.GeneralResponse;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.PaginationSimplified;
import co.com.johan.biblio.gestion_biblioteca.utils.response.ResponseBuilder;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/parameters", produces = { MediaType.APPLICATION_JSON_VALUE })
@RequiredArgsConstructor
public class ParameterController {

    
    private final ParametersService parametersService;

    @GetMapping("/parameter")
    public ResponseEntity<GeneralResponse> getParameterByName(@RequestParam(value = "name", required = true) String param) {
        return ResponseBuilder.of(parametersService.getParameterByName(param), HttpStatus.OK,
                "Parametro obtenido exitosamente");
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse> getAllParams(@RequestParam(value = "pageNumber", required = false , defaultValue = "1") Long pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize) {
        PaginationSimplified<ParameterEntity> result= parametersService.getAllParameters(pageNumber, pageSize);
        return ResponseBuilder.of(result, HttpStatus.OK, "Parametros obtenidos exitosamente");
    }
    

}
