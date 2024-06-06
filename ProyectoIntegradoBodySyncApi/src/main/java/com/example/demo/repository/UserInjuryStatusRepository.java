package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.UserInjuryStatus;

public interface UserInjuryStatusRepository extends JpaRepository<UserInjuryStatus, Integer> {
}