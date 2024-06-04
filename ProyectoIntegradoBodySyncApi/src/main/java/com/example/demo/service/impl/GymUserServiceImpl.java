package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.converter.GymClassConverter;
import com.example.demo.converter.GymUserConverter;
import com.example.demo.entity.GymClass;
import com.example.demo.entity.GymUser;
import com.example.demo.entity.UserInjury;
import com.example.demo.model.GymClassModel;
import com.example.demo.model.GymUserModel;
import com.example.demo.repository.GymUserRepository;
import com.example.demo.repository.UserInjuryRepository;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.GymUserService;

@Service("gymUserService")
public class GymUserServiceImpl implements UserDetailsService, GymUserService {

	@Autowired
	@Qualifier("gymUserRepository")
	private GymUserRepository gymUserRepository;
	
	@Autowired
	@Qualifier("userInjuryRepository")
	private UserInjuryRepository userInjuryRepository;
	
	@Autowired
	@Qualifier("gymUserConverter")
	private GymUserConverter gymUserConverter;
	
	@Autowired
	@Qualifier("gymClassConverter")
	private GymClassConverter gymClassConverter;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		CustomUserDetails builder = null;
		GymUser user = gymUserRepository.findByUsername(email);

		if (user != null) {
			if (user.isEnabled()) {
				builder = new CustomUserDetails(user.getFirstName(), user.getPassword(), 
						Collections.singletonList(new SimpleGrantedAuthority(user.getRole())), user.getId());
			} else {
				throw new DisabledException("El usuario no está activado");
			}
		} else {
			throw new UsernameNotFoundException("Alumno no encontrado con el email: " + email);
		}

		return builder;
	}
	
	public float calculateBMI(GymUserModel user) {
	    if (user.getHeight() != null && user.getWeight() != null) {
	        float heightInMeters = user.getHeight() / 100;
	        return user.getWeight() / (heightInMeters * heightInMeters);
	    } else {
	        return 0;
	    }
	}


	public int calculateAge(Date birthDate) {
	    if (birthDate != null) {
	        LocalDate localBirthDate = convertToLocalDateViaInstant(birthDate);
	        return Period.between(localBirthDate, LocalDate.now()).getYears();
	    } else {
	        return 0;
	    }
	}


	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}

	
	@Override
	public List<GymUserModel> ListAllGymUsers() {
	    List<GymUserModel> users = new ArrayList<>();
	    for (GymUser gymUser : gymUserRepository.findByDeletedAndRole(false,"ROL_GYMUSER")) {
	    	users.add(gymUserConverter.transform(gymUser));
	    }
	    return users;
	}
	
	@Override
	public List<GymUserModel> ListAllGymUsersInstructores() {
	    List<GymUserModel> users = new ArrayList<>();
	    for (GymUser gymUser : gymUserRepository.findByDeletedAndRole(false,"ROL_GYMINSTRUCTOR")) {
	    	users.add(gymUserConverter.transform(gymUser));
	    }
	    return users;
	}

		
	@Override
	public boolean existeUsername(String email) {
		// TODO Auto-generated method stub
		return gymUserRepository.existsByUsername(email);
	}
	
	@Override
	public boolean activarDesactivar(int id) {
		GymUser gymUser = gymUserRepository.findById(id).get();
		if (gymUser != null) {
			if(!gymUser.isEnabled()) 
				gymUser.setEnabled(true);
			else 
				gymUser.setEnabled(false);
			gymUserRepository.save(gymUser);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean eliminarGymUser(int id) {
	    GymUser gymUser = gymUserRepository.findById(id).get();
	    if (gymUser != null) {
	        if(!gymUser.isDeleted()) {
	            gymUser.setDeleted(true);
	            gymUserRepository.save(gymUser);
	            return true;
	        }
	    }
	    return false;
	}

	
	@Override
	public GymUserModel getGymUserById(int id) {
		// TODO Auto-generated method stub
		GymUser gymUser = gymUserRepository.findById(id).get();

		return gymUserConverter.transform(gymUser);
	}
	@Override
	public GymUserModel updateUser(GymUserModel gymUserModel) {
		// TODO Auto-generated method stub
	 
		gymUserRepository.save(gymUserConverter.transform(gymUserModel));
		return gymUserModel;
	}
	
	@Override
	public List<String> getEmails() {
		List<String> emails = new ArrayList<>();
		for (GymUser gymUser : gymUserRepository.findAll()) {
			if (gymUser.isDeleted() && gymUser.getRole().equals("ROL_GYMUSER"))
				emails.add(gymUser.getUsername());
		}
		return emails;
	}
	
	@Override
	public void registrar(GymUserModel gymUser) {
		// TODO Auto-generated method stub
		gymUser.setPassword(passwordEncoder().encode(gymUser.getPassword()));
		gymUserRepository.save(gymUserConverter.transform(gymUser));
		
	}

	public List<GymUserModel> ListGymUsersByClassId(int classId) {
		List<GymUserModel> l = new ArrayList <>();
		for(GymUser g : gymUserRepository.findByEnrolledClasses_Id(classId)) {
			l.add(gymUserConverter.transform(g));
		}
        return l;
    }

	@Override
	public GymUserModel findGymUserByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		   GymUserModel gymUser = gymUserConverter.transform(gymUserRepository.findByUsername(username));
		    
		    // Verificar si el usuario existe
		    if (gymUser == null) {
		        throw new UsernameNotFoundException("Usuario no encontrado");
		    }
		    
		  /*  // Verificar si el usuario esta activado
		    if (gymUser.get == 0) {
		        throw new DisabledException("El usuario no esta activado");
		    }
		    
		    // Verificar si el usuario ha sido borrado
		    if (gymUser == 1) {
		        throw new AccountExpiredException("El usuario ha sido borrado");
		    }*/
		    
		    // Verificar si la contraseña es correcta
		    if (!passwordEncoder().matches(password, gymUser.getPassword())) {
		   
		        throw new BadCredentialsException("Contraseña incorrecta");
		    }
		    
		    return gymUser;
	}

	@Override
	public Set<GymClassModel> enrolledClassesfindbyGymUserModel(Integer id) {
		// TODO Auto-generated method stub
		Set<GymClassModel> l = new HashSet<>();
		for(GymClass g : gymUserRepository.findEnrolledClassesById(id)) {
			l.add(gymClassConverter.transform(g));
		}
		return l;
	}

	  public void updateAttendanceDays(GymUserModel user, Set<Date> attendanceDays) {
	        // Obtener el usuario de la base de datos

	        // Actualizar los días de asistencia del usuario
	        user.setAttendanceDays(attendanceDays);

	        // Guardar los cambios en la base de datos
	        gymUserRepository.save(gymUserConverter.transform(user));
	    }

	@Override
	public int countClassMusculation() {
		// TODO Auto-generated method stub
		int count = 0;
		for(GymUser g : gymUserRepository.findAll()) {
			for(GymClass c : g.getEnrolledClasses())
				if(c.getName().equals("Musculacion"))
					count++;
		}
		return count;
	}

	@Override
	public void updateUserInjury(GymUserModel gymUser,  int userInjuryId) {
		UserInjury userInjury = userInjuryRepository.findById(userInjuryId).get();
		userInjuryRepository.save(userInjury);
		gymUser.getInjuriesList().add(userInjury);
		gymUserRepository.save(gymUserConverter.transform(gymUser));
		
	}

	@Override
	public void updateClassUser(GymUserModel gymUser, GymClassModel c) {
		// TODO Auto-generated method stub
		gymUser.getEnrolledClasses().add(c);
		gymUserRepository.save(gymUserConverter.transform(gymUser));
	}
}
