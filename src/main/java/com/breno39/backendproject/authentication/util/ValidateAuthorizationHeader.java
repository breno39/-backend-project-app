package com.breno39.backendproject.authentication.util;

import java.util.function.Predicate;

public class ValidateAuthorizationHeader implements Predicate<String> {
    @Override
    public boolean test(String authorizationHeaderBody) {
        return !authorizationHeaderBody.isEmpty() && authorizationHeaderBody.startsWith("Bearer");
    }
}
