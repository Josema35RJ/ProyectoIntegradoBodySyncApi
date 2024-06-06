package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.converter.GymUserConverter;
import com.example.demo.converter.UserInjuryStatusConverter;
import com.example.demo.entity.UserInjuryStatus;
import com.example.demo.model.GymUserModel;
import com.example.demo.model.UserInjuryStatusModel;
import com.example.demo.repository.UserInjuryStatusRepository;
import com.example.demo.service.UserInjuryStatusService;

@Service("userInjuryStatusService")
public class UserInjuryStatusServiceImpl implements UserInjuryStatusService{

	@Autowired
	@Qualifier("userInjuryStatusRepository")
    private UserInjuryStatusRepository userInjuryStatusRepository;
	
	@Autowired
	@Qualifier("userInjuryStatusConverter")
    private UserInjuryStatusConverter userInjuryStatusConverter;
	
	@Autowired
	@Qualifier("gymUserConverter")
	private GymUserConverter gymUserConverter;

	@Override
	public List<UserInjuryStatusModel> listUserInjuryStatus() {
		// TODO Auto-generated method stub
		List<UserInjuryStatusModel> l = new ArrayList<>();
		for(UserInjuryStatus u: userInjuryStatusRepository.findAll()) {
			l.add(userInjuryStatusConverter.transform(u));
		}
		return l;
	}

	@Override
	public void addUserInjuryStatus(UserInjuryStatusModel UserInjuryStatus) {
		// TODO Auto-generated method stub
		userInjuryStatusRepository.save(userInjuryStatusConverter.transform(UserInjuryStatus));
	}

	@Override
	public List<UserInjuryStatusModel> listUserInjuryStatusModelByGymUser(GymUserModel gymUser) {
		// TODO Auto-generated method stub
		List<UserInjuryStatusModel> l = new ArrayList<>();
		for(UserInjuryStatus u:userInjuryStatusRepository.findByGymUser(gymUserConverter.transform(gymUser))) {
			l.add(userInjuryStatusConverter.transform(u));
		}
		return l;
	}

}
