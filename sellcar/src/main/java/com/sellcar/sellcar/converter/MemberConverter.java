package com.sellcar.sellcar.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellcar.sellcar.entity.Member;
import com.sellcar.sellcar.request.RegisterRequest;

@Component
public class MemberConverter {
    
    @Autowired
    private ModelMapper modelMapper;

    public Member registerRequestToMember(RegisterRequest request){
        return modelMapper.map(request, Member.class);
    }
}