package co.com.johan.biblio.gestion_biblioteca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import co.com.johan.biblio.gestion_biblioteca.config.filters.JWTValidatorFilter;
import co.com.johan.biblio.gestion_biblioteca.constants.SecurityConstants;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SecurityConfig {

    
 @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security, JWTValidatorFilter jwtValidatorFilter) throws Exception {
        security
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/members/login","/members/login/**").permitAll()
                        .requestMatchers("/members/signup","/members/signup/**").permitAll()
                        .anyRequest().authenticated()
                        )
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtValidatorFilter, BasicAuthenticationFilter.class)
                // .httpBasic(Customizer.withDefaults())
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)

        ;

        return security.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults(SecurityConstants securityConstants){
        return new GrantedAuthorityDefaults(securityConstants.getPrefix());
    }

    @Bean
    public AuthenticationManager authenticationManager(BibGestUserDetailService bibGestUserDetailService, PasswordEncoder passwordEncoder,BibGestUsernamePasswordAuthenticationProvider bibGestUsernamePasswordAuthenticationProvider){
        ProviderManager providerManager=new ProviderManager(bibGestUsernamePasswordAuthenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }

}
