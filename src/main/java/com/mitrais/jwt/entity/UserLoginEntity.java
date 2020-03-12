package com.mitrais.jwt.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "user_login")
public class UserLoginEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_token")
    private String lastToken;

    @Column(name = "last_login_success")
    private Timestamp lastLoginSuccess;

    @Column(name = "last_login_failed")
    private Timestamp lastLoginFailed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLastLoginSuccess() {
        return lastLoginSuccess;
    }

    public void setLastLoginSuccess(Timestamp lastLoginSuccess) {
        this.lastLoginSuccess = lastLoginSuccess;
    }

    public Timestamp getLastLoginFailed() {
        return lastLoginFailed;
    }

    public void setLastLoginFailed(Timestamp lastLoginFailed) {
        this.lastLoginFailed = lastLoginFailed;
    }

    public String getLastToken() {
        return lastToken;
    }

    public void setLastToken(String lastToken) {
        this.lastToken = lastToken;
    }

    @Override
    public String toString() {
        return "UserLoginEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", lastLoginSuccess=" + lastLoginSuccess +
                ", lastLoginFailed=" + lastLoginFailed +
                '}';
    }
}
