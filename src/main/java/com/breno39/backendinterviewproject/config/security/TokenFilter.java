package com.breno39.backendinterviewproject.config.security;

import com.breno39.backendinterviewproject.authentication.util.ValidateAuthorizationHeader;
import com.breno39.backendinterviewproject.config.security.service.TokenService;
import com.breno39.backendinterviewproject.credencial.application.service.CredentialService;
import com.breno39.backendinterviewproject.credencial.domain.Credential;
import com.breno39.backendinterviewproject.handler.ApiException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@AllArgsConstructor
@Log4j2
public class TokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private CredentialService credentialService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("[START] Filter - Filtering request");
        String token = retrieveToken(request);
        authenticateUser(token);
        log.info("[FINISH] Filter - Filtering request");
        filterChain.doFilter(request, response);
    }

    private void authenticateUser(String token) {
        log.info("[START] TokenFilter - authenticateUser");
        Credential credential = retrieveUser(token);
        var authenticationToken = new UsernamePasswordAuthenticationToken(credential, null, credential.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        log.info("[FINISH] TokenFilter - authenticateUser");
    }

    private Credential retrieveUser(String token) {
        var id = tokenService.getId(token)
                                    .orElseThrow(()-> ApiException.throwApiException(HttpStatus.FORBIDDEN, "Invalid Token"));
        return credentialService.findCredentialById(id);
    }

    private String retrieveToken(HttpServletRequest requestOpt) {
        log.info("[START] TokenFilter - retrieveToken");
        var authorizationHeaderValueOpt = Optional.ofNullable(retrieveAuthorizationHeader(requestOpt));
        String authorizationHeaderValue = authorizationHeaderValueOpt.filter(new ValidateAuthorizationHeader())
                .orElseThrow(() -> ApiException.throwApiException(HttpStatus.UNAUTHORIZED, "invalid token"));
        log.info("[FINISH] TokenFilter - retrieveToken");
        return authorizationHeaderValue.substring(7, authorizationHeaderValue.length());
    }

    private String retrieveAuthorizationHeader(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization"))
                .orElseThrow(() -> ApiException.throwApiException(HttpStatus.FORBIDDEN, "Token not present"));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.contains("/public/")||path.contains("/swagger-ui/");
    }

}
