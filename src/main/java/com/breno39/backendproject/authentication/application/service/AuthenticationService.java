package com.breno39.backendproject.authentication.application.service;

import com.breno39.backendproject.authentication.domain.Token;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthenticationService {
    Token authenticate(UsernamePasswordAuthenticationToken userCredentials);
    Token refreshAuthenticationToken(String tokenExpirado);
}
