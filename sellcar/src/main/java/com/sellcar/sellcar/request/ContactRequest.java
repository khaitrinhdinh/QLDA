package com.sellcar.sellcar.request;

import lombok.Getter;

@Getter
public class ContactRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String comment;
}