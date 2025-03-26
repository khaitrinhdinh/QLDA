package com.sellcar.sellcar.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Entity
@Getter
@NoArgsConstructor
public class Admin extends User {
    
}