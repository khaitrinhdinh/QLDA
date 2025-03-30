package com.sellcar.sellcar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SellCar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private User dealer;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @Column(name = "is_sold")
    private Boolean isSold;
}