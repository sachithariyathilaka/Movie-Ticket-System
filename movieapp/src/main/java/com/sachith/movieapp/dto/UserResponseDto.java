package com.sachith.movieapp.dto;

public class UserResponseDto {
    private String token;
    private int id;
    private String message;
    private int status;

    public UserResponseDto(String token, int id, String message, int status){
        this.token = token;
        this.id = id;
        this.message = message;
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
