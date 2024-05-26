package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Workout;
import com.example.demo.model.GymUserModel;

public interface WorkoutService {
	List<Workout> findByGymUser (GymUserModel gymUserModel);
	 List<Workout> ListWorkout ();

}
