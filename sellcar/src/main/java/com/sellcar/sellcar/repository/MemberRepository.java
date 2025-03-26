package com.sellcar.sellcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellcar.sellcar.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    
}