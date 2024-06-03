package com.example.demo.service;

import java.util.List;

import com.example.demo.model.GymUserModel;
import com.example.demo.model.WorkoutModel;

public interface WorkoutService {
	List<WorkoutModel> findByGymUser (GymUserModel gymUserModel);
	 List<WorkoutModel> ListWorkout ();

}
