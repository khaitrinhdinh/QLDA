package com.sellcar.sellcar.config;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class BeanConfig {

    @Value("${cloudinary.api-key}")
    private String cloudinaryApiKey;

    @Value("${cloudinary.api-secret}")
    private String cloudinaryApiSecret;

    @Value("${cloudinary.cloud-name}")
    private String cloudinaryCloudName;
    
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudinaryCloudName);
        config.put("api_key", cloudinaryApiKey);
        config.put("api_secret", cloudinaryApiSecret);
        return new Cloudinary(config);
    }
}