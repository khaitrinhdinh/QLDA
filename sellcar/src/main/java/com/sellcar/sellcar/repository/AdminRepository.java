package com.sellcar.sellcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellcar.sellcar.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    
}