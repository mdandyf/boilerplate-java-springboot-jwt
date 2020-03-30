package com.mitrais.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mitrais.jwt.entity.UserLoginEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Service
public class JWTService {

    private static final String SECRET = "SecretKeyToGenJWTs";
    private static final long EXPIRATION_TIME = 300_000; // 5 minutes
    private static final String TOKEN_PREFIX = "Bearer ";

    public Map<String, Object> getJWTToken(Authentication auth) {
        Date expiryDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(expiryDate)
                .sign(HMAC512(SECRET.getBytes()));

        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("accessToken", token);
        mapResult.put("expiryDate", expiryDate);

        return mapResult;
    }

    public String getParseJWTToken(String token) {
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
    }

}
