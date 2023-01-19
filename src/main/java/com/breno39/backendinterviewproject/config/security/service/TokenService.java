package com.breno39.backendinterviewproject.config.security.service;

import com.breno39.backendinterviewproject.credencial.domain.Credential;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class TokenService {

    @Value("${app.jwt.expiration}")
    private String expiration;
    @Value("${app.jwt.key}")
    private String key;

    public String generateToken(Authentication authentication) {
        return generateToken((Credential) authentication.getPrincipal());
    }

    public String generateToken(Credential credencial) {
        return Jwts.builder()
                .setIssuer("Ninja One Test API")
                .setSubject(credencial.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now()
                        .plusMinutes(Long.valueOf(expiration))
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public Optional<UUID> getId(String token) {
        try {
            log.info("[START] TokenService - Extracting id from token");
            var claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            log.info("[FINISH] TokenService - Extracting id from token");
            return Optional.of(UUID.fromString(claims.getSubject()));
        } catch (SignatureException ex) {
            return Optional.empty();
        } catch (ExpiredJwtException ex) {
            var claims = ex.getClaims();
            log.info("[FINISH] TokenService - Extracting id from token");
            return Optional.of(UUID.fromString(claims.getSubject()));
        }
    }
}
