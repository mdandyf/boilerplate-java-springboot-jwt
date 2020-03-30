package com.mitrais.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitrais.jwt.entity.UserLoginEntity;
import com.mitrais.jwt.model.UserLogin;
import com.mitrais.jwt.service.UserLoginService;
import com.mitrais.jwt.util.JWTService;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class JWTAuthenticationFilter extends CustomUsernamePasswordAuthenticationFilter {

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Access-Token";

    private JWTService jwtService;
    private UserLoginService userLoginService;

    private AuthenticationManager authenticationManager;
    private UserLogin credential;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService, UserLoginService userLoginService, String url) {
        super(url, HttpMethod.POST.name());
        this.jwtService = jwtService;
        this.userLoginService = userLoginService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            this.credential = new ObjectMapper().readValue(request.getInputStream(), UserLogin.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            this.credential.getUsername(),
                            this.credential.getPass())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        Map<String, Object> mapToken = jwtService.getJWTToken(auth);
        UserLoginEntity userLoginEntity = userLoginService.updateUserTokenSuccess(this.credential, mapToken);
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + userLoginEntity.getAccessToken());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        userLoginService.updateUserTokenFailed(credential);
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
