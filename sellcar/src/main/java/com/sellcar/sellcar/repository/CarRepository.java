package com.sellcar.sellcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellcar.sellcar.entity.Car;
import com.sellcar.sellcar.repository.custom.CarRepositoryCustom;

public interface CarRepository extends JpaRepository<Car, Integer>, CarRepositoryCustom {
    
}