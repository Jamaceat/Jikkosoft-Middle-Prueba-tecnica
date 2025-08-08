package co.com.johan.biblio.gestion_biblioteca.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Component
@PropertySource(value= "classpath:security/security.properties")
@ConfigurationProperties(prefix = "security")
@Getter
@Setter
@Validated
public class SecurityConstants {

    @NotBlank
    private String prefix;

    @NotBlank
    private String requestHeader;


}
