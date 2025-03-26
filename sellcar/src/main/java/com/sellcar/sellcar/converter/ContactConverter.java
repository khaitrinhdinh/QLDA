package com.sellcar.sellcar.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellcar.sellcar.entity.Contact;
import com.sellcar.sellcar.request.ContactRequest;

@Component
public class ContactConverter {
    
    @Autowired
    private ModelMapper modelMapper;

    public Contact contactHomeRequestToContact(ContactRequest request){
        return modelMapper.map(request, Contact.class);
    }
}