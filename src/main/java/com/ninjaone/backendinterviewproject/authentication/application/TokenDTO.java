package com.ninjaone.backendinterviewproject.authentication.application;

import com.ninjaone.backendinterviewproject.authentication.domain.Token;
import lombok.Value;

@Value
public class TokenDTO {
    private String token;
    private String type;

    public TokenDTO(Token token){
        this.token=token.getToken();
        this.type =token.getType();
    }
}
