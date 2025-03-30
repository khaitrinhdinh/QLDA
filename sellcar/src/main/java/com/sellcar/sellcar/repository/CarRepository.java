package com.sellcar.sellcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import com.sellcar.sellcar.entity.Car;
import com.sellcar.sellcar.repository.custom.CarRepositoryCustom;
import java.util.List;
public interface CarRepository extends JpaRepository<Car, Integer>, CarRepositoryCustom {
    @Query("SELECT c FROM Car c JOIN Cart ct ON c.id = ct.car.id WHERE ct.user.id = :userId")
    List<Car> findByUserId(@Param("userId") Integer userId);
    
    @Query("SELECT c FROM Car c WHERE c.id IN (SELECT s.car.id FROM SellCar s WHERE s.dealer.id = :id)")
    List<Car> findByDealerId(@Param("id") Integer id);
    
}