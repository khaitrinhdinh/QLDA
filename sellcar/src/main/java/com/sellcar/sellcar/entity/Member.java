package com.sellcar.sellcar.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
public class Member extends User {
    
}