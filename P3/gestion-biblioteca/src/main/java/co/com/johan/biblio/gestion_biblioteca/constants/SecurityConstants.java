package co.com.johan.biblio.gestion_biblioteca.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Component
@PropertySource(value= "classpath:security/security.properties")
@ConfigurationProperties(prefix = "security")
@Getter
@Setter
@Validated
@NoArgsConstructor
public class SecurityConstants {

    @NotBlank
    private String prefix;

    @NotBlank   
    private String requestHeader;

    @NotBlank
    private String jwtSecret;

    @NotNull
    private Long tokenDurationMinutes;

    @NotBlank
    private String ZoneOffset;

    @NotBlank
    private String issuer;

}
