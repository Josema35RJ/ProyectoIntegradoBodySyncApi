package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.NutritionPlan;
import com.example.demo.model.GymUserModel;

public interface NutritionPlanService {
	 NutritionPlan findByGymUser (GymUserModel gymUserModel);
	 List<NutritionPlan> ListNutritionPlan ();

}
