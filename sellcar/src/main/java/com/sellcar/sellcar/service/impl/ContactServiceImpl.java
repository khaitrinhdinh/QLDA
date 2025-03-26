package com.sellcar.sellcar.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellcar.sellcar.converter.ContactConverter;
import com.sellcar.sellcar.entity.Contact;
import com.sellcar.sellcar.entity.Dealer;
import com.sellcar.sellcar.repository.ContactRepository;
import com.sellcar.sellcar.repository.DealerRepository;
import com.sellcar.sellcar.request.ContactRequest;
import com.sellcar.sellcar.service.ContactService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactConverter contactConverter;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private DealerRepository dealerRepository;

    @Override
    public Boolean sendContactHome(ContactRequest request) {
        try{
            contactRepository.save(contactConverter.contactHomeRequestToContact(request));
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean senContactDealer(ContactRequest request, Integer dealerId) {
        try{
            Contact contact = contactConverter.contactHomeRequestToContact(request);
            Optional<Dealer> dealerOp = dealerRepository.findById(dealerId);
            if(dealerOp.isPresent()){
                contact.setDealer(dealerOp.get());
                contactRepository.save(contact);
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
}