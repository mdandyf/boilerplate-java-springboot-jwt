package com.mitrais.jwt.repository;

import com.mitrais.jwt.entity.UserLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginEntity, Long> {
    UserLoginEntity findByUsername(String username);
}
