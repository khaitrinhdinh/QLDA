package com.sellcar.sellcar.response;

import java.util.List;

import com.sellcar.sellcar.dto.FeatureDTO;
import com.sellcar.sellcar.entity.Feature;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingResponse {
    SearchCarResponse searchCarResponse;
    List<FeatureDTO> featureDTOs;
}