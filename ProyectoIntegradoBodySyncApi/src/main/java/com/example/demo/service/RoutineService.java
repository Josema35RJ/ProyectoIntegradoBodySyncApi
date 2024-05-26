package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Routine;
import com.example.demo.model.GymUserModel;

public interface RoutineService {
	Routine findByGymUser (GymUserModel gymUserModel);
	 List<Routine> ListRoutine ();

}
