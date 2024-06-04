package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.GymClass;
import com.example.demo.entity.GymUser;

@Repository("gymUserRepository")
public interface GymUserRepository extends JpaRepository<GymUser, Integer> {

    boolean existsByUsername(String email);
	
    List<GymUser> findByDeletedAndRole(boolean x,String role);
	GymUser findByUsername(String email);
	List<GymUser> findByEnrolledClasses_Id(int classId);
	
	 List<GymUser> findByRole( String role);

	Integer countByAttendanceDaysContains(String day);

	Integer countByEnabled(boolean b);

	GymUser findByUsernameAndPassword(String email, String password);

	 @Query("SELECT g.enrolledClasses FROM GymUser g WHERE g.id = :id")
	    Set<GymClass> findEnrolledClassesById(@Param("id") int id);

}
