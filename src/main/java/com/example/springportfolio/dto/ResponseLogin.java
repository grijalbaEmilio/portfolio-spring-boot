package com.example.springportfolio.dto;

import com.example.springportfolio.model.User;

public class ResponseLogin {
    private User user;
    private String token;

    private ResponseLogin() {}
    public ResponseLogin(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ResponseLogin{" +
                "user=" + user +
                ", token='" + token + '\'' +
                '}';
    }
}
