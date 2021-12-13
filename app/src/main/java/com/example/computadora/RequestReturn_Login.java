package com.example.computadora;

import java.util.Date;

public class RequestReturn_Login {
    private String token;
    private RequestReturn_User user;

    public RequestReturn_Login(String token, RequestReturn_User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RequestReturn_User getUser() {
        return user;
    }

    public void setUser(RequestReturn_User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "RequestReturn_Login{" +
                "token='" + token + '\'' +
                ", user=" + user +
                '}';
    }
}
