package com.sellcar.sellcar.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellcar.sellcar.dto.CarImageDTO;
import com.sellcar.sellcar.entity.CarImage;

@Component
public class CarImageConverter {
    
    @Autowired
    private ModelMapper modelMapper;

    public CarImageDTO carImageToCarImageDTO(CarImage carImage){
        return modelMapper.map(carImage, CarImageDTO.class);
    }
}