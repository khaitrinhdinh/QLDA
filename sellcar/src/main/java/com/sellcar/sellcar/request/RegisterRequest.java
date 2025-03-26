package com.sellcar.sellcar.request;

import lombok.Getter;

@Getter
public class RegisterRequest {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
}