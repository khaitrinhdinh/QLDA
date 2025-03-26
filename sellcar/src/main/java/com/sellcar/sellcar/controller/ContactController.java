package com.sellcar.sellcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sellcar.sellcar.request.ContactRequest;
import com.sellcar.sellcar.service.ContactService;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {
    
    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<?> sendContactHome(@RequestBody ContactRequest request){
        if(contactService.sendContactHome(request)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @PostMapping("/dealer")
    public ResponseEntity<?> sendContactDealer(@RequestBody ContactRequest request, @RequestParam Integer id){
        if(contactService.senContactDealer(request, id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }
}