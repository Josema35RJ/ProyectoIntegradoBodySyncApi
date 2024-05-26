package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.GymUser;
import com.example.demo.entity.Routine;
import com.example.demo.entity.Workout;

@Repository("workoutRepository")
public interface WorkoutRepository extends JpaRepository<Workout, Serializable> {

	List<Workout> findByGymUser(GymUser gymUser);

}
