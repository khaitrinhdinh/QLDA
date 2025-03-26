package com.sellcar.sellcar.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Dealer extends User {
    
    @Builder.Default
    @OneToMany(mappedBy = "dealer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Contact> contacts = new HashSet<>();
}