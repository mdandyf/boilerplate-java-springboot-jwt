package com.mitrais.jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserAuthController {

    @PostMapping("/auth")
    public ResponseEntity userAuthorization() {
        return new ResponseEntity("User Authorization is successful", HttpStatus.OK);
    }
}
