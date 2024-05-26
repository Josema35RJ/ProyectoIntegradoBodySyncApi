package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.converter.GymUserConverter;
import com.example.demo.converter.UserInjuryConverter;
import com.example.demo.entity.UserInjury;
import com.example.demo.model.GymUserModel;
import com.example.demo.repository.UserInjuryRepository;
import com.example.demo.service.UserInjuryService;

@Service("userInjuryService")
public class UserInjuryServiceImpl implements UserInjuryService{

	@Autowired
	@Qualifier("userInjuryRepository")
    private UserInjuryRepository userInjuryRepository;
	
	@Autowired
	@Qualifier("userInjuryConverter")
    private UserInjuryConverter userInjuryConverter;
	
	@Autowired
	@Qualifier("gymUserConverter")
	private GymUserConverter gymUserConverter;

	@Override
	public UserInjury findByGymUser(GymUserModel gymUserModel) {
		// TODO Auto-generated method stub
		return userInjuryRepository.findByGymUser(gymUserConverter.transform(gymUserModel));
	}

	@Override
	public List<UserInjury> ListUserInjury() {
		// TODO Auto-generated method stub
		return userInjuryRepository.findAll();
	}

	


}
