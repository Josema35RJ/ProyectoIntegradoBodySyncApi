package com.example.demo.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.GymClass;
import com.example.demo.entity.GymUser;
import com.example.demo.entity.NutritionPlan;
import com.example.demo.entity.Routine;
import com.example.demo.entity.UserInjury;
import com.example.demo.entity.Workout;
import com.example.demo.service.GymClassService;
import com.example.demo.service.GymUserService;
import com.example.demo.service.NutritionPlanService;
import com.example.demo.service.RoutineService;
import com.example.demo.service.UserInjuryService;
import com.example.demo.service.WorkoutService;

@RestController
public class RestApiController {

	@Autowired
	@Qualifier("gymUserService")
	private GymUserService gymUserService;

	@Autowired
	@Qualifier("gymClassService")
	private GymClassService gymClassService;

	@Autowired
	@Qualifier("nutritionPlanService")
	private NutritionPlanService nutritionPlanService;

	@Autowired
	@Qualifier("workoutService")
	private WorkoutService workoutService;
	
	@Autowired
	@Qualifier("routineService")
	private RoutineService routineService;

	@Autowired
	@Qualifier("userInjuryService")
	private UserInjuryService userInjuryService;
	
	@GetMapping("/apiGymUser/getClases/{id}")
	public ResponseEntity<?> ListClasesGymUser(@PathVariable int id, Principal principal) {
		Map<String, Object> response = new HashMap<>();
		try {
			// Comprobar si el usuario autenticado es el mismo que el idAlumno
			if (!principal.getName().equals(gymUserService.getGymUserById(id).getUsername())) {
				response.put("success", false);
				response.put("message", "No tienes permiso para ver estos servicios");
				return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
			}
			Set<GymClass> clases = gymUserService.enrolledClassesfindbyGymUserModel(id);// Obtener los servicios de la

			response.put("success", true);
			response.put("data", clases);
			response.put("message", "Clases obtenidas con exito");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/apiGymInstructor/getClases/{id}")
	public ResponseEntity<?> ListClasesGymInstructor(@PathVariable int id, Principal principal) {
		Map<String, Object> response = new HashMap<>();
		try {
			// Comprobar si el usuario autenticado es el mismo que el idAlumno
			if (!principal.getName().equals(gymUserService.getGymUserById(id).getUsername())) {
				response.put("success", false);
				response.put("message", "No tienes permiso para ver estos servicios");
				return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
			}
			List<GymClass> clases = gymClassService.getAllClassesFinByInstructorId(gymUserService.getGymUserById(id));// Obtener																														// los																														// servicios																													// d																												// la
			response.put("success", true);
			response.put("data", clases);
			response.put("message", "Clases obtenidas con exito");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/apiGymInstructor/getMiembros/{id}")
	public ResponseEntity<?> ListGymUserGymInstructor(@PathVariable int id, Principal principal) {
		Map<String, Object> response = new HashMap<>();
		try {
			// Comprobar si el usuario autenticado es el mismo que el idAlumno
			if (!principal.getName().equals(gymClassService.getClassById(id).getInstructor().getUsername())) {
				response.put("success", false);
				response.put("message", "No tienes permiso para ver estos Miembros de esa Clase");
				return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
			}

			List<GymUser> clases = gymUserService.ListGymUsersByClassId(id);// Obtener los servicios de la

			response.put("success", true);
			response.put("data", clases);
			response.put("message", "Miembros obtenidas con exito");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/apiGymUser/ListClass")
	public ResponseEntity<?> ListGymClass() {
		Map<String, Object> response = new HashMap<>();
		try {

			List<GymClass> clases = gymClassService.getAllClasses();// Obtener los servicios de la

			response.put("success", true);
			response.put("data", clases);
			response.put("message", "Clases obtenidas con exito");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/apiGymUser/NutritionPlan/{id}")
	public ResponseEntity<?> ListNutritionPlanByGymUser(@PathVariable int id, Principal principal) {
		Map<String, Object> response = new HashMap<>();
		try {

			// Comprobar si el usuario autenticado es el mismo que el idAlumno
			if (!principal.getName().equals(gymClassService.getClassById(id).getInstructor().getUsername())) {
				response.put("success", false);
				response.put("message", "No tienes permiso para ver estos Miembros de esa Clase");
				return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
			}
			NutritionPlan nutritionPlan = nutritionPlanService.findByGymUser(gymUserService.getGymUserById(id)); // de
																													// la

			response.put("success", true);
			response.put("data", nutritionPlan);
			response.put("message", "Plan de nutricion obtenido con exito");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/apiGymUser/NutritionPlan")
	public ResponseEntity<?> ListNutritionPlan() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<NutritionPlan> nutritionPlan = nutritionPlanService.ListNutritionPlan();
			response.put("data", nutritionPlan);
			response.put("message", "Plan de nutricion obtenido con exito");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/apiGymUser/Workout/{id}")
	public ResponseEntity<?> ListWorkoutByGymUser(@PathVariable int id, Principal principal) {
		Map<String, Object> response = new HashMap<>();
		try {
			// Comprobar si el usuario autenticado es el mismo que el idAlumno
			if (!principal.getName().equals(gymClassService.getClassById(id).getInstructor().getUsername())) {
				response.put("success", false);
				response.put("message", "No tienes permiso para ver estos Miembros de esa Clase");
				return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
			}
			List<Workout> workout = workoutService.findByGymUser(gymUserService.getGymUserById(id));
			response.put("data", workout);
			response.put("message", "Entrenamientos del GymUser obtenido con exito");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/apiGymUser/Workout")
	public ResponseEntity<?> ListWorkout() {
		Map<String, Object> response = new HashMap<>();
		try {
			
			List<Workout> workout = workoutService.ListWorkout();
			response.put("data", workout);
			response.put("message", "Entrenamientos obtenidos con exito");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/apiGymUser/Routine")
	public ResponseEntity<?> ListRoutine() {
		Map<String, Object> response = new HashMap<>();
		try {
			
			List<Routine> ListRoutine = routineService.ListRoutine();
			response.put("data", ListRoutine);
			response.put("message", "Rutinas obtenidos con exito");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/apiGymUser/UserInjury")
	public ResponseEntity<?> ListUserInjury() {
		Map<String, Object> response = new HashMap<>();
		try {
			
			List<UserInjury> ListUserInjury = userInjuryService.ListUserInjury();
			response.put("data", ListUserInjury);
			response.put("message", "Lesiones obtenidos con exito");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}