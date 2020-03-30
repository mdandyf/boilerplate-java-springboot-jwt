package com.mitrais.jwt.service;

import com.mitrais.jwt.entity.UserLoginEntity;
import com.mitrais.jwt.mapper.UserLoginMapper;
import com.mitrais.jwt.model.UserLogin;
import com.mitrais.jwt.repository.UserLoginRepository;
import org.slf4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@Service
public class UserLoginService {

    private static Logger logger;

    private UserLoginRepository userLoginRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserLoginMapper userLoginMapper;

    public UserLoginService(UserLoginRepository userLoginRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserLoginMapper userLoginMapper) {
        this.userLoginRepository = userLoginRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userLoginMapper = userLoginMapper;
    }

    public UserLoginEntity setUserSignUp(UserLogin user) {
        user.setPass(bCryptPasswordEncoder.encode(user.getPass()));
        UserLoginEntity userLoginEntity = userLoginMapper.mapUserLoginToEntity(user);
        return userLoginRepository.save(userLoginEntity);
    }

    public UserLoginEntity updateUserTokenSuccess(UserLogin userLogin, Map<String, Object> map) {
        UserLoginEntity userLoginEntity = userLoginRepository.findByUsername(userLogin.getUsername());
        try {
            String accessToken =(String) map.entrySet().stream().filter(e -> e.getKey().contains("accessToken")).findFirst().get().getValue();
            Date expiryDate = (Date) map.entrySet().stream().filter(e -> e.getKey().contains("expiryDate")).findFirst().get().getValue();

            userLoginEntity.setAccessToken(accessToken);
            userLoginEntity.setExpiryDate(new Timestamp(expiryDate.getTime()));
            userLoginEntity.setLastLoginSuccess(new Timestamp(new Date().getTime()));
            return userLoginRepository.save(userLoginEntity);
        } catch (Exception e) {
            //TODO give logger
            return userLoginEntity;
        }
    }

    public UserLoginEntity updateUserTokenFailed(UserLogin userLogin) {
        UserLoginEntity userLoginEntity = userLoginRepository.findByUsername(userLogin.getUsername());
        userLoginEntity.setLastLoginFailed(new Timestamp(new Date().getTime()));
        return userLoginRepository.save(userLoginEntity);
    }
}
