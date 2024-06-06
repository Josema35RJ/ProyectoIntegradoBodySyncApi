package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.MealLog;

@Repository("mealLogRepository")
public interface MealLogRepository  extends JpaRepository<MealLog, Integer>{
    
}
