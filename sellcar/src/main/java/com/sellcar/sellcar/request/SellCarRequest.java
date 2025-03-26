package com.sellcar.sellcar.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SellCarRequest {
    private String title;
    private String condition;
    private String year;
    private Integer capacity;
    private Long price;
    private String description;
    private List<String> featureCodes;
    private String anotherFeature;
    private String address;
    private List<MultipartFile> images;
    private Integer userId; 
}