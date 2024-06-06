package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.converter.MealLogConverter;
import com.example.demo.entity.MealLog;
import com.example.demo.model.MealLogModel;
import com.example.demo.repository.MealLogRepository;
import com.example.demo.service.MealLogService;

@Service("mealLogService")
public class MealLogServiceImpl implements MealLogService {

	  @Autowired
	    @Qualifier("mealLogRepository")
	    private MealLogRepository mealLogRepository;
	  
	  @Autowired
	    @Qualifier("mealLogConverter")
	    private MealLogConverter mealLogConverter;

	@Override
	public List<MealLogModel> listMealLog() {
		// TODO Auto-generated method stub
		List<MealLogModel> l = new ArrayList<>();
		for(MealLog m : mealLogRepository.findAll()) {
			l.add(mealLogConverter.transform(m));
		}
		return l;
	}

	@Override
	public void addMealLogModel(MealLogModel mealLog) {
		// TODO Auto-generated method stub
		mealLogRepository.save(mealLogConverter.transform(mealLog));
	}

	@Override
	public void deleteMealLogModel(Integer mealLogId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMealLogModel(Integer mealLogId) {
		// TODO Auto-generated method stub
		
	}


}
