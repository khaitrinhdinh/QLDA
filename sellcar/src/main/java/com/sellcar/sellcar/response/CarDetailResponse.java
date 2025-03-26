package com.sellcar.sellcar.response;

import java.util.List;

import com.sellcar.sellcar.dto.CarImageDTO;
import com.sellcar.sellcar.dto.FeatureDTO;
import com.sellcar.sellcar.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarDetailResponse {
    private String title;
    private List<CarImageDTO> imageUrls;
    private String description;
    private Long price;
    private List<FeatureDTO> features;
    private String year;
    private Integer capacity;
    private UserDTO dealerInfo;
    private Integer quantityEvaluate;
    private Double rates;
    private String condition;
    private String address;
}