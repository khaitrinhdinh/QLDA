package com.sellcar.sellcar.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellcar.sellcar.dto.CarImageDTO;
import com.sellcar.sellcar.dto.FeatureDTO;
import com.sellcar.sellcar.dto.UserDTO;
import com.sellcar.sellcar.entity.Car;
import com.sellcar.sellcar.entity.Evaluate;
import com.sellcar.sellcar.enumerate.ConditionCarCode;
import com.sellcar.sellcar.repository.EvaluateRepository;
import com.sellcar.sellcar.request.SellCarRequest;
import com.sellcar.sellcar.response.CarDetailResponse;
import com.sellcar.sellcar.response.SearchCarResponse;

@Component
public class CarConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FeatureConverter featureConverter;

    @Autowired
    private CarImageConverter carImageConverter;

    @Autowired
    private EvaluateRepository evaluateRepository;

    @Autowired
    private UserConverter userConverter;

    public Car sellCarRequestToCar(SellCarRequest request) {
        Car car = Car.builder().address(request.getAddress())
                .capacity(request.getCapacity())
                .description(request.getDescription())
                .price(request.getPrice())
                .title(request.getTitle())
                .year(request.getYear())
                .build();
        car.setCondition(ConditionCarCode.valueOf(request.getCondition().toUpperCase()));
        return car;
    }

    public CarDetailResponse carToCarDetailResponse(Car car) {
        CarDetailResponse carDetailResponse = modelMapper.map(car, CarDetailResponse.class);
        List<FeatureDTO> featureDTOs = car.getFeatures().stream()
                .map(feature -> featureConverter.featureToFeatureDTO(feature)).collect(Collectors.toList());
        List<CarImageDTO> carImageDTOs = car.getCarImages().stream()
                .map(carImage -> carImageConverter.carImageToCarImageDTO(carImage)).collect(Collectors.toList());
        UserDTO userDTO = userConverter.userToUserDTO(car.getUser());
        List<Evaluate> evaluates = evaluateRepository.findByCar_Id(car.getId());
        Double rates = 0.0;
        for (Evaluate evaluate : evaluates) {
            rates += evaluate.getRate();
        }
        carDetailResponse.setQuantityEvaluate(evaluates.size());
        carDetailResponse.setRates(rates / evaluates.size());
        carDetailResponse.setFeatures(featureDTOs);
        carDetailResponse.setImageUrls(carImageDTOs);
        carDetailResponse.setDealerInfo(userDTO);
        carDetailResponse.setCondition(car.getCondition().name());
        return carDetailResponse;
    }

    public SearchCarResponse carToSearchCarResponse(Car car) {
        return modelMapper.map(car, SearchCarResponse.class);
    }
}