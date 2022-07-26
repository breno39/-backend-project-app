package com.ninjaone.backendinterviewproject.authentication.application;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class AuthenticationForm {

    @NotBlank
    private String username;
    @Size(min = 4)
    @NotNull
    private String password;

    public UsernamePasswordAuthenticationToken converter(){
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
