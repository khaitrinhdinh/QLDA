package com.sellcar.sellcar.service;

import java.util.List;

import com.sellcar.sellcar.request.SearchCarRequest;
import com.sellcar.sellcar.request.SellCarRequest;
import com.sellcar.sellcar.response.CarDetailResponse;
import com.sellcar.sellcar.response.SearchCarResponse;
import com.sellcar.sellcar.response.TrainingResponse;

public interface CarService {
    
    public Boolean sellCar(SellCarRequest request);

    public CarDetailResponse getCarById(Integer id);

    public List<SearchCarResponse> searchCar(SearchCarRequest request);

    public List<TrainingResponse> getTrainingData();

}