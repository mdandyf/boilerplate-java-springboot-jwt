package com.mitrais.jwt.service;

import com.mitrais.jwt.entity.UserLoginEntity;
import com.mitrais.jwt.repository.UserLoginRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserLoginService {
    private UserLoginRepository userLoginRepository;

    public UserLoginService(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    public UserLoginEntity updateUserLoginToken(String username, String token) {
        UserLoginEntity userLoginEntity = userLoginRepository.findByUsername(username);
        userLoginEntity.setLastToken(token);
        userLoginEntity.setLastLoginSuccess(new Timestamp(new Date().getTime()));
        userLoginRepository.save(userLoginEntity);
        return userLoginEntity;
    }
}
