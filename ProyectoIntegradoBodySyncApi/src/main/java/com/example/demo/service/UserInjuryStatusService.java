package com.example.demo.service;

import java.util.List;

import com.example.demo.model.GymUserModel;
import com.example.demo.model.UserInjuryStatusModel;

public interface UserInjuryStatusService {
    List<UserInjuryStatusModel> listUserInjuryStatus();
 
    List<UserInjuryStatusModel> listUserInjuryStatusModelByGymUser(GymUserModel gymUser);
	void addUserInjuryStatus(Integer gymUserId, Integer userInjuryId, boolean isActive);

	void updateUserInjuryStatus(Integer gymUserId, Integer userInjuryId, boolean isActive);
}

