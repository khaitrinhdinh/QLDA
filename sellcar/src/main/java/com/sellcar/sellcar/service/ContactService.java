package com.sellcar.sellcar.service;

import com.sellcar.sellcar.request.ContactRequest;

public interface ContactService {
    
    public Boolean sendContactHome(ContactRequest request);

    public Boolean senContactDealer(ContactRequest request, Integer dealerId);

}