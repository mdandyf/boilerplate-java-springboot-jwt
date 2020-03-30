package com.mitrais.jwt.controller;

import com.mitrais.jwt.model.UserLogin;
import com.mitrais.jwt.service.UserLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserLoginController {

    private UserLoginService userLoginService;

    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody UserLogin user) {
        userLoginService.setUserSignUp(user);
        return new ResponseEntity("User has signed up successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLogin user) {
        return new ResponseEntity(user.getUsername() + " has logged in successfully", HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity userAuthorization() {
        return new ResponseEntity("User Authorization is successful", HttpStatus.OK);
    }
}
