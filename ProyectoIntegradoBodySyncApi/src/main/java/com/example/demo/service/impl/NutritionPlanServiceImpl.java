package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.converter.GymUserConverter;
import com.example.demo.converter.NutritionPlanConverter;
import com.example.demo.entity.NutritionPlan;
import com.example.demo.model.GymUserModel;
import com.example.demo.repository.NutritionPlanRepository;
import com.example.demo.service.NutritionPlanService;

@Service("nutritionPlanService")
public class NutritionPlanServiceImpl implements NutritionPlanService{

	@Autowired
	@Qualifier("nutritionPlanRepository")
    private NutritionPlanRepository nutritionPlanRepository;
	
	@Autowired
	@Qualifier("nutritionPlanConverter")
    private NutritionPlanConverter nutritionPlanConverter;
	
	@Autowired
	@Qualifier("gymUserConverter")
	private GymUserConverter gymUserConverter;

	@Override
	public NutritionPlan findByGymUser(GymUserModel gymUserModel) {
		// TODO Auto-generated method stub
		return nutritionPlanRepository.findByGymUser(gymUserConverter.transform(gymUserModel));
	}

	@Override
	public List<NutritionPlan> ListNutritionPlan() {
		// TODO Auto-generated method stub
		return nutritionPlanRepository.findAll();
	}
	


}
