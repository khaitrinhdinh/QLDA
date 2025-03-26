package com.sellcar.sellcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sellcar.sellcar.service.EvaluateService;

@RestController
@RequestMapping("/api/v1/evaluate")
public class EvaluateController {
    
    @Autowired
    private EvaluateService evaluateService;

    @PostMapping
    public ResponseEntity<?> evaluateCar(@RequestParam Integer userId, @RequestParam Integer carId, @RequestParam Integer rate){
        if(evaluateService.evaluateCar(carId, userId, rate)){
            return ResponseEntity.ok().build();
        } 
        return ResponseEntity.internalServerError().build();
    }
}