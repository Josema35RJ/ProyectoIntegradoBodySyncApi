package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.converter.GymUserConverter;
import com.example.demo.converter.WorkoutConverter;
import com.example.demo.entity.Workout;
import com.example.demo.model.GymUserModel;
import com.example.demo.model.WorkoutModel;
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
	public List<WorkoutModel> findByGymUser(GymUserModel gymUserModel) {
		// TODO Auto-generated method stub
		List<WorkoutModel> l = new ArrayList<>();
		for(Workout w : workoutRepository.findByGymUser(gymUserConverter.transform(gymUserModel))) {
			l.add(workoutConverter.transform(w));
		}
		return l;
	}

	@Override
	public List<WorkoutModel> ListWorkout() {
		// TODO Auto-generated method stub
		List<WorkoutModel> l = new ArrayList<>();
		for(Workout w : workoutRepository.findAll()) {
			l.add(workoutConverter.transform(w));
		}
		return l;
	}

}
