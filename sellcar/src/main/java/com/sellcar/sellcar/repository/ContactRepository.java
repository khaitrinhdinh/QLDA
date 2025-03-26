package com.sellcar.sellcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellcar.sellcar.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    
}