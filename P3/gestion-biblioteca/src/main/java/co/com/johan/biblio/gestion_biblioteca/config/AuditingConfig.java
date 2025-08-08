package co.com.johan.biblio.gestion_biblioteca.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class AuditingConfig {
    

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            Optional<Authentication> authentication = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());

            if (authentication.isEmpty() || !authentication.get().isAuthenticated() || "anonymousUser".equals(authentication.get().getPrincipal())) {
                return Optional.of("SYSTEM");
            }
            

            return Optional.of(authentication.get().getName());
        };
    }


}
