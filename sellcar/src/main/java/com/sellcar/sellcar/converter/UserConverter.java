package com.sellcar.sellcar.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellcar.sellcar.dto.UserDTO;
import com.sellcar.sellcar.entity.Admin;
import com.sellcar.sellcar.entity.Dealer;
import com.sellcar.sellcar.entity.Member;
import com.sellcar.sellcar.entity.User;

@Component
public class UserConverter {
    
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO userToUserDTO(User user){
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        if(user instanceof Member){
            userDTO.setRole("Member");
        } else if (user instanceof Admin){
            userDTO.setRole("Admin");
        } else if (user instanceof Dealer) {
            userDTO.setRole("Dealer");
        } else {
            throw new RuntimeException("Tài khoản chưa được phân quyền!!!");
        }
        return userDTO;
    }
}