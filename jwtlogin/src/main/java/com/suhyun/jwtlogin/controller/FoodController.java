package com.suhyun.jwtlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suhyun.jwtlogin.domain.Food;
import com.suhyun.jwtlogin.persistence.FoodRepository;

@RestController
public class FoodController {

    @Autowired
    private FoodRepository foodRepo;

    @GetMapping("/getFoods")
    public Iterable<Food> getFoods() {
        return foodRepo.findAll();
    }
    
}
