package com.mitrais.jwt.service;

import com.mitrais.jwt.entity.UserLoginEntity;
import com.mitrais.jwt.repository.UserLoginRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserLoginRepository userLoginRepository;

    public UserDetailServiceImpl(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLoginEntity userLoginEntity = userLoginRepository.findByUsername(username);
        if(userLoginEntity == null) {
            throw new UsernameNotFoundException("Unable to find username " + username);
        }
        return new User(userLoginEntity.getUsername(), userLoginEntity.getPassword(), Collections.emptyList());
    }
}
