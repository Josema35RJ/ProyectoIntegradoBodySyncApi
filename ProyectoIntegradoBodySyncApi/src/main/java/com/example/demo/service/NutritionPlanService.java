package com.example.demo.service;

import java.util.List;

import com.example.demo.model.GymUserModel;
import com.example.demo.model.NutritionPlanModel;

public interface NutritionPlanService {
	 NutritionPlanModel findByGymUser (GymUserModel gymUserModel);
	 List<NutritionPlanModel> ListNutritionPlan ();

}
