package com.suhyun.jwtlogin.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suhyun.jwtlogin.domain.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    
}
