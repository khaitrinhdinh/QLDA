package com.sellcar.sellcar.repository.custom;

import java.sql.SQLException;
import java.util.List;

import com.sellcar.sellcar.entity.Car;
import com.sellcar.sellcar.request.SearchCarRequest;

public interface CarRepositoryCustom {
    public List<Car> findBySearchCarRequest(SearchCarRequest request) throws SQLException;
}