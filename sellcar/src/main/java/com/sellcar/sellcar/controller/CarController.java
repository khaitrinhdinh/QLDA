package com.sellcar.sellcar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sellcar.sellcar.entity.Car;
import com.sellcar.sellcar.request.SearchCarRequest;
import com.sellcar.sellcar.request.SellCarRequest;
import com.sellcar.sellcar.response.CarDetailResponse;
import com.sellcar.sellcar.response.SearchCarResponse;
import com.sellcar.sellcar.response.TrainingResponse;
import com.sellcar.sellcar.service.CarService;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    
    @Autowired
    private CarService carService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> sellCar(@ModelAttribute SellCarRequest request){
        if(carService.sellCar(request)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDetailResponse> getDetailCar(@PathVariable(value = "id") Integer id){
        CarDetailResponse carDetailResponse = carService.getCarById(id);
        if(carDetailResponse != null){
            return ResponseEntity.ok(carDetailResponse);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PostMapping
    public ResponseEntity<List<SearchCarResponse>> searchCar(@RequestBody SearchCarRequest request){
        List<SearchCarResponse> searchCarResponses = carService.searchCar(request);
        if(searchCarResponses != null){
            return ResponseEntity.ok(searchCarResponses);
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/training")
    public ResponseEntity<List<TrainingResponse>> getTrainingData(){
        List<TrainingResponse> searchCarResponses = carService.getTrainingData();
        if(searchCarResponses != null){
            return ResponseEntity.ok(searchCarResponses);
        }
        return ResponseEntity.internalServerError().build();
    }
    @GetMapping("/cart/{id}")
    public ResponseEntity<List<CarDetailResponse>> getCartByUserId(@PathVariable(value = "id") Integer userId) {
        List<CarDetailResponse> cartItems = carService.getCartByUserId(userId);
        if (cartItems != null && !cartItems.isEmpty()) {
            return ResponseEntity.ok(cartItems);
        }
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/sellcar/{id}")
    public ResponseEntity<CarDetailResponse> getCarById(@PathVariable Integer id) {
        CarDetailResponse car = carService.getCarById(id);
        if (car == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(car);
    }

}