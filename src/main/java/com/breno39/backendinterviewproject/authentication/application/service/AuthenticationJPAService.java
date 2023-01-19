package com.breno39.backendinterviewproject.authentication.application.service;

import java.util.UUID;

import com.breno39.backendinterviewproject.credencial.application.service.CredentialService;
import com.breno39.backendinterviewproject.credencial.domain.Credential;
import com.breno39.backendinterviewproject.handler.ApiException;
import com.breno39.backendinterviewproject.authentication.domain.Token;
import com.breno39.backendinterviewproject.config.security.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Service
@Log4j2
public class AuthenticationJPAService implements AuthenticationService {
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private CredentialService credentialService;

    @Override
    public Token authenticate(UsernamePasswordAuthenticationToken userCredentials) {
        log.info("[START] AuthenticationJPAService - authenticate");
        var authentication = authenticationManager.authenticate(userCredentials);
        log.info("[FINISH] AuthenticationJPAService - authenticate");
        return Token.builder()
                .type("Bearer")
                .token(tokenService.generateToken(authentication))
                .build();
    }

    @Override
    public Token refreshAuthenticationToken(String tokenExpirado) {
        log.info("[START] AuthenticationJPAService - refreshAuthenticationToken");
        var id = getId(tokenExpirado);
        Credential credencial = credentialService.findCredentialById(id);
        log.info("[FINISH] AuthenticationJPAService - refreshAuthenticationToken");
        return Token.builder().type("Bearer")
                .token(tokenService.generateToken(credencial))
                .build();
    }

    private UUID getId(String tokenExpirado){
        return tokenService.getId(tokenExpirado)
                .orElseThrow(()-> ApiException.throwApiException(HttpStatus.FORBIDDEN, "Invalid Token"));
    }

}
