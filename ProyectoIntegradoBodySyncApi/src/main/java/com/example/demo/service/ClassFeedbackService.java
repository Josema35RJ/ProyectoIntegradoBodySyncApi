package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ClassFeedbackModel;

public interface ClassFeedbackService {
    List<ClassFeedbackModel> getFeedbackByGymClassId(int classId);
    void addFeedback(ClassFeedbackModel feedback);
	void addFeedbackUser(Integer gymUserId, Integer gymClassId, Integer rating, String comment);
}

