package com.teamphoenix.pustok_onlinebookshop.entity;

public class UserToken {
    String email, token;

    public UserToken(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public UserToken() {
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
