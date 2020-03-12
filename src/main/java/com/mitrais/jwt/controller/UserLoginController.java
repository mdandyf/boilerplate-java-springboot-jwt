package com.mitrais.jwt.controller;

import com.mitrais.jwt.entity.UserLoginEntity;
import com.mitrais.jwt.mapper.UserLoginMapper;
import com.mitrais.jwt.model.UserLogin;
import com.mitrais.jwt.repository.UserLoginRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {

    private UserLoginRepository userLoginRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserLoginMapper userLoginMapper;

    public UserLoginController(UserLoginRepository userLoginRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserLoginMapper userLoginMapper) {
        this.userLoginRepository = userLoginRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userLoginMapper = userLoginMapper;
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody UserLogin user) {
        user.setPass(bCryptPasswordEncoder.encode(user.getPass()));
        UserLoginEntity userLoginEntity = userLoginMapper.mapUserLoginToEntity(user);
        userLoginRepository.save(userLoginEntity);
        return new ResponseEntity("User sign up is successful", HttpStatus.OK);
    }

}
