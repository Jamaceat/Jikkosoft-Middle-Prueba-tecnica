package co.com.johan.biblio.gestion_biblioteca.utils.mappers;

import org.springframework.stereotype.Component;

@Component
public class IdentityMapper {
    
    public <T> T map(T source) {
        return source;
    }
}
