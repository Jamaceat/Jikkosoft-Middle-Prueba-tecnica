package co.com.johan.biblio.gestion_biblioteca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;




@Component
@RequiredArgsConstructor
public class BibGestUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    
    private final BibGestUserDetailService bibGestUserDetailService;

    
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = bibGestUserDetailService.loadUserByUsername(email);

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());
        } else {

            throw new BadCredentialsException("Contraseña incorrecta");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {

        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);

    }

}
