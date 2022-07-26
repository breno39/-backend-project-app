package com.ninjaone.backendinterviewproject.authentication.util;

import java.util.function.Predicate;

public class ValidateAuthorizationHeader implements Predicate<String> {
    @Override
    public boolean test(String AuthorizationHeaderBody) {
        return !AuthorizationHeaderBody.isEmpty() && AuthorizationHeaderBody.startsWith("Bearer");
    }
}
