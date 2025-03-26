package com.sellcar.sellcar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellcar.sellcar.entity.Evaluate;

public interface EvaluateRepository extends JpaRepository<Evaluate, Integer> {
    
    public List<Evaluate> findByCar_Id(Integer id);
}