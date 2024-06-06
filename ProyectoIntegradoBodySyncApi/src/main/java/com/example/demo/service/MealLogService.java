package com.example.demo.service;

import java.util.List;

import com.example.demo.model.MealLogModel;

public interface MealLogService {
 List<MealLogModel> listMealLog ();
 void addMealLogModel (MealLogModel mealLog);
 void deleteMealLogModel (Integer mealLogId);
 void updateMealLogModel (Integer mealLogId);

}
