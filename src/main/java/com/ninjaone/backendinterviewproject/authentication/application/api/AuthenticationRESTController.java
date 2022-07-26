package com.ninjaone.backendinterviewproject.authentication.application.api;

import com.ninjaone.backendinterviewproject.authentication.application.AuthenticationForm;
import com.ninjaone.backendinterviewproject.authentication.application.TokenDTO;
import com.ninjaone.backendinterviewproject.authentication.application.service.AuthenticationService;
import com.ninjaone.backendinterviewproject.authentication.util.ValidateAuthorizationHeader;
import com.ninjaone.backendinterviewproject.handler.ApiException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@Log4j2
public class AuthenticationRESTController implements AuthenticationAPI {

    private AuthenticationService authenticationService;

    @Override
    public ResponseEntity<TokenDTO> Authenticate(AuthenticationForm authenticationForm) {
        log.info("[START] AuthenticationRESTController - Authenticate");
        var token = authenticationService.authenticate(authenticationForm.converter());
        log.info("[FINISH] AuthenticationRESTController - Authenticate");
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @Override
    public ResponseEntity<TokenDTO> refreshAuthentication(String expiredToken) throws AuthenticationException {
        log.info("[START] AuthenticationRESTController - refreshAuthentication");
        String tokenExpiradoValido= validateExpiredToken(Optional.of(expiredToken));
        var token = authenticationService.refreshAuthenticationToken(tokenExpiradoValido);
        log.info("[FINISH] AuthenticationRESTController - refreshAuthentication");
        return ResponseEntity.ok(new TokenDTO(token));
    }

    private String validateExpiredToken(Optional<String> tokenExpirado){
        String tokenExp= tokenExpirado.filter(new ValidateAuthorizationHeader())
                    .orElseThrow(() -> ApiException.throwApiException(HttpStatus.BAD_REQUEST,"Invalid Token"));
        return tokenExp.substring(7);
    }

}
