package com.mitrais.jwt.mapper;

import com.mitrais.jwt.entity.UserLoginEntity;
import com.mitrais.jwt.model.UserLogin;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class UserLoginMapper {
    public UserLoginEntity mapUserLoginToEntity(UserLogin user) {
        UserLoginEntity userLoginEntity = new UserLoginEntity();
        userLoginEntity.setUsername(user.getUsername());
        userLoginEntity.setPassword(user.getPass());
        userLoginEntity.setLastLoginSuccess(new Timestamp(new Date().getTime()));
        return userLoginEntity;
    }
}
