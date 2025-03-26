package com.sellcar.sellcar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Integer id;
    private String phoneNumber;
    private String email;
    private String fullName;
    private String role;
}