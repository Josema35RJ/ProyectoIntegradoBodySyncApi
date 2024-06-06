package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.GymUser;
import com.example.demo.entity.UserInjuryStatus;
import com.example.demo.model.UserInjuryStatusModel;

public interface UserInjuryStatusRepository extends JpaRepository<UserInjuryStatus, Integer> {

	List<UserInjuryStatus> findByGymUser(GymUser gymUser);
}