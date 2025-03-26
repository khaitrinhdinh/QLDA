package com.sellcar.sellcar.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellcar.sellcar.dto.FeatureDTO;
import com.sellcar.sellcar.entity.Feature;

@Component
public class FeatureConverter {
    
    @Autowired
    private ModelMapper modelMapper;

    public FeatureDTO featureToFeatureDTO(Feature feature){
        return modelMapper.map(feature, FeatureDTO.class);
    }
}