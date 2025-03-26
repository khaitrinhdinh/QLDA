package com.sellcar.sellcar.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellcar.sellcar.converter.MemberConverter;
import com.sellcar.sellcar.converter.UserConverter;
import com.sellcar.sellcar.dto.UserDTO;
import com.sellcar.sellcar.entity.User;
import com.sellcar.sellcar.repository.MemberRepository;
import com.sellcar.sellcar.repository.UserRepository;
import com.sellcar.sellcar.request.LoginRequest;
import com.sellcar.sellcar.request.RegisterRequest;
import com.sellcar.sellcar.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberConverter memberConverter;

    @Override
    public UserDTO login(LoginRequest request) {
        try{
            Optional<User> userOp = userRepository.findByEmail(request.getEmail());
            if(userOp.isPresent()){
                User user = userOp.get();
                if(request.getPassword().equals(user.getPassword())){
                    return userConverter.userToUserDTO(user);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean register(RegisterRequest request) {
        try{
            memberRepository.save(memberConverter.registerRequestToMember(request));
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
}