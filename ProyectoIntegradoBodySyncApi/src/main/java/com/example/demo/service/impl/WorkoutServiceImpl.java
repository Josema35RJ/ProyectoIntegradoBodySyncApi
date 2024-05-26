package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.converter.GymUserConverter;
import com.example.demo.converter.WorkoutConverter;
import com.example.demo.entity.Workout;
import com.example.demo.model.GymUserModel;
import com.example.demo.repository.WorkoutRepository;
import com.example.demo.service.WorkoutService;

@Service("workoutService")
public class WorkoutServiceImpl implements WorkoutService{

	@Autowired
	@Qualifier("workoutRepository")
    private WorkoutRepository workoutRepository;
	
	@Autowired
	@Qualifier("workoutConverter")
    private WorkoutConverter workoutConverter;
	
	@Autowired
	@Qualifier("gymUserConverter")
	private GymUserConverter gymUserConverter;

	@Override
	public List<Workout> findByGymUser(GymUserModel gymUserModel) {
		// TODO Auto-generated method stub
		return workoutRepository.findByGymUser(gymUserConverter.transform(gymUserModel));
	}

	@Override
	public List<Workout> ListWorkout() {
		// TODO Auto-generated method stub
		return workoutRepository.findAll();
	}

}
