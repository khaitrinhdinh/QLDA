package com.sellcar.sellcar.repository.custom.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sellcar.sellcar.entity.Car;
import com.sellcar.sellcar.enumerate.ConditionCarCode;
import com.sellcar.sellcar.repository.custom.CarRepositoryCustom;
import com.sellcar.sellcar.request.SearchCarRequest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CarRepositoryCustomImpl implements CarRepositoryCustom {

    @PersistenceContext // inject đối tượng EntityManager vào do spring boot quản lý vòng đời của nó
    private EntityManager entityManager;

    @Override
    public List<Car> findBySearchCarRequest(SearchCarRequest request) throws SQLException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = criteriaBuilder.createQuery(Car.class);
        Root<Car> root = query.from(Car.class);
        List<Predicate> predicates = new ArrayList<>();

        if (request.getTitle() != null && !request.getTitle().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + request.getTitle() + "%"));
        }

        if(request.getCondition() != null && !request.getCondition().isBlank()){
            predicates.add(criteriaBuilder.equal(root.get("condition"), ConditionCarCode.valueOf(request.getCondition())));
        }

        List<Predicate> yearPredicates = new ArrayList<>();
        if(request.getYears() != null && !request.getYears().isEmpty()){
            for(String year : request.getYears()){
                yearPredicates.add(criteriaBuilder.equal(root.get("year"), year));
            }
            predicates.add(criteriaBuilder.or(yearPredicates.toArray(new Predicate[0])));
        }

        if(request.getPriceFrom() != null){
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(root.get("price"), request.getPriceFrom()));
        }

        if(request.getPriceTo() != null){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), request.getPriceTo()));
        }

        if(request.getCapacity() != null){
            predicates.add(criteriaBuilder.equal(root.get("capacity"), request.getCapacity()));
        }

        if (!predicates.isEmpty()) {
            Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            query.where(finalPredicate);
        }

        query.select(root).distinct(true);
        List<Car> cars = new ArrayList<>();

        try {
            cars = entityManager.createQuery(query).getResultList();
            return cars;
        } catch (Exception e) {
            throw new SQLException("Error: " + e.getMessage());
        }
    }
    
}