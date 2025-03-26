package com.sellcar.sellcar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellcar.sellcar.entity.CarImage;

public interface CarImageRepository extends JpaRepository<CarImage, Integer> {
    public List<CarImage> findByCar_Id(Integer id);
}