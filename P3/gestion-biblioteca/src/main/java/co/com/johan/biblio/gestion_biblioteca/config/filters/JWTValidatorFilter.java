package co.com.johan.biblio.gestion_biblioteca.config.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.johan.biblio.gestion_biblioteca.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTValidatorFilter extends OncePerRequestFilter {

    
    private final SecurityConstants securityConstants;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = request.getHeader(securityConstants.getRequestHeader());
        String secret = securityConstants.getJwtSecret();
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        if (Objects.isNull(jwt) || !jwt.startsWith("Bearer ")) {
            throw new BadCredentialsException("Token invalido");
        }
        jwt = jwt.replaceFirst("Bearer ", "");

        try {

            if (Objects.nonNull(secretKey)) {
                Claims claims = Jwts
                        .parser()
                        .verifyWith(secretKey)
                        .build()
                        .parseSignedClaims(jwt)
                        .getPayload();

                String email = claims.get("email", String.class);
                String authorities = claims.get("authorities", String.class);

                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
            filterChain.doFilter(request, response);
        } catch (JwtException ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Estado 401
            response.setContentType("application/json");

            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("message", ex.getMessage());

            response.getWriter().write(objectMapper.writeValueAsString(errorDetails));
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().contains("/login") || request.getServletPath().contains("/signup");
    }

}
