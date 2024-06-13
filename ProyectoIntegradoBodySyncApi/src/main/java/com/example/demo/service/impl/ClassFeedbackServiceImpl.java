package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.converter.ClassFeedbackConverter;
import com.example.demo.entity.ClassFeedback;
import com.example.demo.model.ClassFeedbackModel;
import com.example.demo.repository.ClassFeedbackRepository;
import com.example.demo.service.ClassFeedbackService;
import com.example.demo.service.GymClassService;
import com.example.demo.service.GymUserService;

@Service("classFeedbackService")
public class ClassFeedbackServiceImpl implements ClassFeedbackService {
 
	@Autowired
	@Qualifier("classFeedbackRepository")
    private ClassFeedbackRepository classFeedbackRepository;
	
	@Autowired
	@Qualifier("gymUserService")
	private GymUserService gymUserService;
	
	@Autowired
	@Qualifier("gymClassService")
	private GymClassService gymClassService;
	
	@Autowired
	@Qualifier("classFeedbackConverter")
	private ClassFeedbackConverter classFeedbackConverter;	

    @Override
    public List<ClassFeedbackModel> getFeedbackByGymClassId(int classId) {
         List<ClassFeedbackModel> l = new ArrayList<>();
    	for(ClassFeedback c : classFeedbackRepository.findByGymClassId(classId)) {
    		l.add(classFeedbackConverter.transform(c));
    	}
        return l;
    }

    @Override
    public void addFeedback(ClassFeedbackModel feedback) {
        classFeedbackRepository.save(classFeedbackConverter.transform(feedback));
    }

	@Override
	public void addFeedbackUser(Integer gymUserId, Integer gymClassId, Integer rating, String comment) {
		// TODO Auto-generated method stub
		ClassFeedbackModel n = new ClassFeedbackModel(0,gymUserService.getGymUserById(gymUserId), gymClassService.getClassById(gymClassId), rating, comment);
		 classFeedbackRepository.save(classFeedbackConverter.transform(n));
	}
}