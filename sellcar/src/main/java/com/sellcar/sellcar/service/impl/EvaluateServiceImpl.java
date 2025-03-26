package com.sellcar.sellcar.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellcar.sellcar.entity.Car;
import com.sellcar.sellcar.entity.Evaluate;
import com.sellcar.sellcar.entity.User;
import com.sellcar.sellcar.repository.CarRepository;
import com.sellcar.sellcar.repository.EvaluateRepository;
import com.sellcar.sellcar.repository.UserRepository;
import com.sellcar.sellcar.service.EvaluateService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EvaluateRepository evaluateRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean evaluateCar(Integer carId, Integer userId, Integer rate) {
        try{
            Optional<User> userOp = userRepository.findById(userId);
            Optional<Car> carOp = carRepository.findById(carId);
            if(userOp.isPresent() && carOp.isPresent()){
                evaluateRepository.save(Evaluate.builder().car(carOp.get()).rate(rate).user(userOp.get()).build());
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
}