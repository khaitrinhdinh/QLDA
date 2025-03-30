package com.sellcar.sellcar.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sellcar.sellcar.entity.SellCar;
public interface SellCarRepository extends JpaRepository<SellCar, Integer> {
    List<SellCar> findByDealer_Id(Integer id);
}
