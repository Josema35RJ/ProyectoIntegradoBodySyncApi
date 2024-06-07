 package com.example.demo.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.example.demo.entity.GymClass;
import com.example.demo.entity.GymUser;
import com.example.demo.entity.UserInjury;
import com.example.demo.model.GymClassModel;
import com.example.demo.model.GymUserModel;

public interface GymUserService {

	 List<GymUserModel> ListAllGymUsers();
	 public List<GymUserModel> ListAllGymUsersInstructores();
	 public int calculateAge(Date birthDate);
	 public float calculateBMI(GymUserModel user);
	 public LocalDate convertToLocalDateViaInstant(Date dateToConvert);
	   public void registrar(GymUserModel gymUser);
	   public boolean existeUsername(String email);
	   public boolean activarDesactivar(int gymUserId);
	   public boolean eliminarGymUser(int id);
	   public GymUserModel getGymUserById(int id);
	   GymUserModel updateUser (GymUserModel gymUserModel);
	   List<String> getEmails();
	   List<GymUserModel> ListGymUsersByClassId(int classId);
	GymUserModel findGymUserByUsernameAndPassword(String username, String password);
	Set<GymClassModel> enrolledClassesfindbyGymUserModel(Integer id);
	void updateAttendanceDays(GymUserModel user, Set<Date> attendanceDays);
	int countClassMusculation();
	void updateClassUser(GymUserModel gymUser, GymClassModel c);
	void addInjuryToUser(GymUserModel user, Integer injuryId);
	void updateUserInjury(GymUserModel gymUser, int userInjuryId);
	void addMealLog(GymUserModel gymUserById, Integer userMealLogId);
}
