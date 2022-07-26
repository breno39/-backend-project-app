package com.ninjaone.backendinterviewproject.authentication.application.service;

import com.ninjaone.backendinterviewproject.authentication.domain.Token;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthenticationService {
    Token authenticate(UsernamePasswordAuthenticationToken userCredentials);
    Token refreshAuthenticationToken(String tokenExpirado);
}
