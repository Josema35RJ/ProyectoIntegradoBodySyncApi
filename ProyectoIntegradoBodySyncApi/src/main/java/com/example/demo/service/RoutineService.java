package com.example.demo.service;

import java.util.List;

import com.example.demo.model.GymUserModel;
import com.example.demo.model.RoutineModel;

public interface RoutineService {
	RoutineModel findByGymUser (GymUserModel gymUserModel);
	 List<RoutineModel> ListRoutine ();

}
