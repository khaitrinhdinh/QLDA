package com.sellcar.sellcar.service;

import com.sellcar.sellcar.dto.UserDTO;
import com.sellcar.sellcar.request.LoginRequest;
import com.sellcar.sellcar.request.RegisterRequest;

public interface UserService {
    public UserDTO login(LoginRequest request);
    public Boolean register(RegisterRequest request);
}