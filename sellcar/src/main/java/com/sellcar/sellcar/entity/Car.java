package com.sellcar.sellcar.entity;

import java.util.HashSet;
import java.util.Set;

import com.sellcar.sellcar.enumerate.ConditionCarCode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String year;

    private String address;

    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_condition", nullable = false)
    @Builder.Default
    private ConditionCarCode condition = ConditionCarCode.OLD;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Column(nullable = false)
    private Long price;

    private Integer capacity;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CarImage> carImages = new HashSet<>();

    @ManyToMany
    @Builder.Default
    @JoinTable(
        name = "car_feature",
        joinColumns = @JoinColumn(name = "car_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "feature_id", nullable = false)
    )
    private Set<Feature> features = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Evaluate> evaluates = new HashSet<>();
}