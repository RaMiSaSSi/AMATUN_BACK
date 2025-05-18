package com.example.demo.dto;

public class AuthenticationResponse {
    private String jwt;
    private String role;
    private long userId;
    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public AuthenticationResponse(String jwt, String role, long userId) {
        this.jwt = jwt;
        this.role = role;
        this.userId = userId;
    }
}
