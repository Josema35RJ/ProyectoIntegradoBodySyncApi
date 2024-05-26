package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.GymUser;
import com.example.demo.entity.UserInjury;

@Repository("userInjuryRepository")
public interface UserInjuryRepository extends JpaRepository<UserInjury, Serializable> {

	UserInjury findByGymUser(GymUser gymUser);

}
