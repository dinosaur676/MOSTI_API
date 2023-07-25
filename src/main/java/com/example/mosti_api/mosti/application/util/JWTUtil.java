package com.example.mosti_api.mosti.application.util;

import org.springframework.security.oauth2.jwt.Jwt;

public class JWTUtil {
    static public String getUserId(Jwt token) {
        return token.getClaimAsString("sub");
    }

    static public String getEmail(Jwt token) {
        return token.getClaimAsString("email");
    }
}
