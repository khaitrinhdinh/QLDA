package com.sellcar.sellcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sellcar.sellcar.dto.UserDTO;
import com.sellcar.sellcar.request.LoginRequest;
import com.sellcar.sellcar.request.RegisterRequest;
import com.sellcar.sellcar.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequest request){
        UserDTO userDTO = userService.login(request);
        if( userDTO != null){
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        if(userService.register(request)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }
}