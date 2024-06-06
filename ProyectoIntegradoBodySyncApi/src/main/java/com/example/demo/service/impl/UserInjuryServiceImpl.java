package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.converter.GymUserConverter;
import com.example.demo.converter.UserInjuryConverter;
import com.example.demo.entity.UserInjury;
import com.example.demo.model.UserInjuryModel;
import com.example.demo.repository.UserInjuryRepository;
import com.example.demo.repository.UserInjuryStatusRepository;
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
	
	@Autowired
	@Qualifier("userInjuryStatusRepository")
	private UserInjuryStatusRepository userInjuryStatusRepository;

	@Override
	public List<UserInjuryModel> ListUserInjury() {
		// TODO Auto-generated method stub
		List<UserInjuryModel> l = new ArrayList<>();
		for(UserInjury u : userInjuryRepository.findAll()) {
			l.add(userInjuryConverter.transform(u));
		}
		return l;
	}

	@Override
	public UserInjuryModel getUserInjuryById(Integer userInjuryId) {
		// TODO Auto-generated method stub
		 
		return userInjuryConverter.transform(userInjuryRepository.findById(userInjuryId).get());
	}
}
