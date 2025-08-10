package co.com.johan.biblio.gestion_biblioteca.members.services.impl;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import co.com.johan.biblio.gestion_biblioteca.constants.SecurityConstants;
import co.com.johan.biblio.gestion_biblioteca.members.dtos.request.LoginRequestR;
import co.com.johan.biblio.gestion_biblioteca.members.dtos.request.RegisterMemberR;
import co.com.johan.biblio.gestion_biblioteca.members.entities.MemberEntity;
import co.com.johan.biblio.gestion_biblioteca.members.repository.MemberRepository;
import co.com.johan.biblio.gestion_biblioteca.members.services.MemberService;
import co.com.johan.biblio.gestion_biblioteca.utils.mappers.MemberMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityConstants securityConstants;

    private static String EMAIL_FIELD = "email";

    private static String AUTHORITIES_FIELD = "authorities";

    @Transactional
    @Override
    public MemberEntity registerMember(RegisterMemberR registerMemberR) {

        MemberEntity member = memberMapper.memberPrepareEntity(registerMemberR);
        member = memberRepository.save(member);
        return member;
    }

    @Override
    public String login(LoginRequestR loginRequest) {
        String jwt = "";

        Authentication authentication = UsernamePasswordAuthenticationToken
                .unauthenticated(loginRequest.email(), loginRequest.password());

        Authentication authenticationResponse = authenticationManager.authenticate(authentication);

        if (Objects.nonNull(authenticationResponse) && authenticationResponse.isAuthenticated()) {

            String secret = securityConstants.getJwtSecret();
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            log.info("ZoneOffset: {}", securityConstants.getZoneOffset());

            ZonedDateTime date = ZonedDateTime.now(java.time.ZoneId.of(securityConstants.getZoneOffset()));
            Date issuedAt = Date.from(date.toInstant());

            jwt = Jwts.builder()
                    .issuer(securityConstants.getIssuer())
                    .issuedAt(issuedAt)
                    .claim(EMAIL_FIELD, authenticationResponse.getName())
                    .claim(AUTHORITIES_FIELD,
                            authenticationResponse.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.joining(",")))
                    .expiration(Date.from(
                            date.plus(securityConstants.getTokenDurationMinutes(), ChronoUnit.MINUTES).toInstant()))
                    .signWith(secretKey)
                    .compact();

        }

        return jwt;
    }

}
