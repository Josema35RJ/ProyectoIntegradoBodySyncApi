package com.example.demo.service;

import java.util.List;

import com.example.demo.model.GymUserModel;
import com.example.demo.model.UserInjuryStatusModel;

public interface UserInjuryStatusService {
    List<UserInjuryStatusModel> listUserInjuryStatus();
    void addUserInjuryStatus(UserInjuryStatusModel UserInjuryStatus);
    List<UserInjuryStatusModel> listUserInjuryStatusModelByGymUser(GymUserModel gymUser);
}

