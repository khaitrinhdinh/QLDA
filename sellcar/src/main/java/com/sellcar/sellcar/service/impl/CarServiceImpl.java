package com.sellcar.sellcar.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sellcar.sellcar.converter.CarConverter;
import com.sellcar.sellcar.dto.FeatureDTO;
import com.sellcar.sellcar.entity.Car;
import com.sellcar.sellcar.entity.CarImage;
import com.sellcar.sellcar.entity.Evaluate;
import com.sellcar.sellcar.entity.Feature;
import com.sellcar.sellcar.entity.User;
import com.sellcar.sellcar.repository.CarImageRepository;
import com.sellcar.sellcar.repository.CarRepository;
import com.sellcar.sellcar.repository.EvaluateRepository;
import com.sellcar.sellcar.repository.FeatureRepository;
import com.sellcar.sellcar.repository.UserRepository;
import com.sellcar.sellcar.request.SearchCarRequest;
import com.sellcar.sellcar.request.SellCarRequest;
import com.sellcar.sellcar.response.CarDetailResponse;
import com.sellcar.sellcar.response.SearchCarResponse;
import com.sellcar.sellcar.response.TrainingResponse;
import com.sellcar.sellcar.service.CarService;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarConverter carConverter;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private CarImageRepository carImageRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private Cloudinary cloudinary ;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EvaluateRepository evaluateRepository;

    @Override
    public Boolean sellCar(SellCarRequest request) {
        try {
            Car car = carConverter.sellCarRequestToCar(request);
            Set<Feature> features = new HashSet<>(featureRepository.findByCodeIn(request.getFeatureCodes()));
            if (request.getAnotherFeature() != null && !request.getAnotherFeature().isEmpty()) {
                Feature another = featureRepository
                        .save(Feature.builder().name(request.getAnotherFeature()).code("OTHER").build());
                features.add(another);
            }
            List<String> urlImages = uploadFiles(request.getImages());
            car.getFeatures().addAll(features);
            User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại!!!"));
            car.setUser(user);
            Car newCar = carRepository.save(car);
            List<CarImage> carImages = urlImages.stream()
            .map(urlImage -> CarImage.builder().imageUrl(urlImage).car(newCar).build()).collect(Collectors.toList());
            carImages = carImageRepository.saveAll(carImages);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CarDetailResponse getCarById(Integer id) {
        try{
            Optional<Car> carOp = carRepository.findById(id);
            if(carOp.isPresent()){
                Car car = carOp.get();
                return carConverter.carToCarDetailResponse(car);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SearchCarResponse> searchCar(SearchCarRequest request) {
        try{
            List<Car> cars = carRepository.findBySearchCarRequest(request);
            return cars.stream().map(car -> {
                SearchCarResponse searchCarResponse = carConverter.carToSearchCarResponse(car);
                List<Evaluate> evaluates = evaluateRepository.findByCar_Id(car.getId());
                Double rates = 0.0;
                for(Evaluate evaluate : evaluates){
                    rates += evaluate.getRate();
                }
                List<CarImage> carImages = carImageRepository.findByCar_Id(car.getId());
                searchCarResponse.setQuantityEvaluate(evaluates.size());
                searchCarResponse.setRates(rates / evaluates.size());
                searchCarResponse.setFirstImage(carImages.size() > 0? carImages.getFirst().getImageUrl() : "");
                return searchCarResponse;
            }).collect(Collectors.toList());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private List<String> uploadFiles(List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                urls.add(uploadResult.get("secure_url").toString());
            } catch (IOException e) {
                throw new RuntimeException("Upload failed: " + e.getMessage());
            }
        }
        return urls;
    }

    @Override
    public List<TrainingResponse> getTrainingData() {
        try{
            List<SearchCarResponse> searchCarResponses = searchCar(new SearchCarRequest());
            List<TrainingResponse> trainingResponses = new ArrayList<>();
            for(SearchCarResponse searchCarResponse : searchCarResponses){
                List<FeatureDTO> featureDTOs = featureRepository.findByCar_Id(searchCarResponse.getId()).stream()
                .map(feature -> FeatureDTO.builder().code(feature.getCode()).id(feature.getId()).name(feature.getName()).build())
                .collect(Collectors.toList());
                TrainingResponse trainingResponse = TrainingResponse.builder()
                .searchCarResponse(searchCarResponse)
                .featureDTOs(featureDTOs)
                .build();
                trainingResponses.add(trainingResponse);
            }
            return trainingResponses;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<CarDetailResponse> getCartByUserId(Integer userId) {
        try {
            List<Car> cars = carRepository.findByUserId(userId);
            return cars.stream()
                    .map(carConverter::carToCarDetailResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    @Override
    public List<CarDetailResponse> getCarsByDealer(Integer id) {
        try {
            List<Car> cars = carRepository.findByDealerId(id);
            return cars.stream()
                    .map(carConverter::carToCarDetailResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}