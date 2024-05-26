package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.UserInjury;
import com.example.demo.model.GymUserModel;

public interface UserInjuryService {
	UserInjury findByGymUser (GymUserModel gymUserModel);
	 List<UserInjury> ListUserInjury ();

}
