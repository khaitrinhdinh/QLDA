package com.sellcar.sellcar.response;

import com.sellcar.sellcar.enumerate.ConditionCarCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchCarResponse {
    private Integer id;
    private String title;
    private ConditionCarCode condition;
    private Long price;
    private String address;
    private String year;
    private Integer capacity;
    private Integer quantityEvaluate;
    private Double rates;
    private String firstImage;
} 