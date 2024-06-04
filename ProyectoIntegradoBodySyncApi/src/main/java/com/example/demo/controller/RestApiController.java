package com.example.demo.controller;

import java.security.Principal;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserInjury;
import com.example.demo.model.ExerciseModel;
import com.example.demo.model.GymClassModel;
import com.example.demo.model.GymUserModel;
import com.example.demo.model.NutritionPlanModel;
import com.example.demo.model.RoutineModel;
import com.example.demo.model.UserInjuryModel;
import com.example.demo.model.WorkoutModel;
import com.example.demo.service.ExerciseService;
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
	@Qualifier("exerciseService")
	private ExerciseService exerciseService;
	
	@Autowired
	@Qualifier("routineService")
	private RoutineService routineService;

	@Autowired
	@Qualifier("userInjuryService")
	private UserInjuryService userInjuryService;
	
	@GetMapping("/apiGymUser/getGymUser/{id}")
	public ResponseEntity<?> GymUser(@PathVariable int id, Principal principal) {
		Map<String, Object> response = new HashMap<>();
		try {
			// Comprobar si el usuario autenticado es el mismo que el idAlumno
			if (!principal.getName().equals(gymUserService.getGymUserById(id).getUsername())) {
				response.put("success", false);
				response.put("message", "No tienes permiso para ver este Usuario");
				return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
			}
			GymUserModel gymUser = gymUserService.getGymUserById(id);// Obtener los servicios de la

			response.put("success", true);
			response.put("data", gymUser);
			response.put("message", "Usuario obtenido con exito");
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
			List<GymClassModel> clases = gymClassService.getAllClassesFinByInstructorId(gymUserService.getGymUserById(id));// Obtener																														// los																														// servicios																													// d																												// la
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

			List<GymUserModel> clases = gymUserService.ListGymUsersByClassId(id);// Obtener los servicios de la

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

			List<GymClassModel> clases = gymClassService.getAllClasses();// Obtener los servicios de la

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
			NutritionPlanModel nutritionPlan = nutritionPlanService.findByGymUser(gymUserService.getGymUserById(id)); // de
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
			List<NutritionPlanModel> nutritionPlan = nutritionPlanService.ListNutritionPlan();
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
			List<WorkoutModel> workout = workoutService.findByGymUser(gymUserService.getGymUserById(id));
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
			
			List<WorkoutModel> workout = workoutService.ListWorkout();
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
			
			List<RoutineModel> ListRoutine = routineService.ListRoutine();
			response.put("data", ListRoutine);
			response.put("message", "Rutinas obtenidos con exito");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/apiGymUser/CountMusculationUsers")
    public int countClassMusculation() {
        int countUsers = gymUserService.countClassMusculation();
        return countUsers;
    }
	
	@GetMapping("/apiGymUser/UserInjury")
	public ResponseEntity<?> ListUserInjury() {
		Map<String, Object> response = new HashMap<>();
		try {
			
			List<UserInjuryModel> ListUserInjury = userInjuryService.ListUserInjury();
			response.put("data", ListUserInjury);
			response.put("message", "Lesiones obtenidos con exito");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/apiGymUser/UserInjury/{id}")
	public ResponseEntity<?> addUserInjury(@PathVariable int id, @RequestBody String userInjuryId, Principal principal) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        // Comprobar si el usuario autenticado es el mismo que el id del usuario
	        if (!principal.getName().equals(gymUserService.getGymUserById(id).getUsername())) {
	            response.put("success", false);
	            response.put("message", "No tienes permiso para actualizar los días de asistencia de este usuario");
	            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	        }
	        int injuryId = Integer.valueOf(userInjuryId); // Convertir la cadena a entero
	        gymUserService.updateUserInjury(gymUserService.getGymUserById(id), injuryId);
	        response.put("success", true);
	        response.put("message", "Dolor o molestia asignada con éxito");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	@PutMapping("/apiGymUser/updateAttendanceDays/{id}")
    public ResponseEntity<?> updateAttendanceDays(@PathVariable int id, @RequestBody Set<Date> attendanceDays, Principal principal) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Comprobar si el usuario autenticado es el mismo que el id del usuario
            if (!principal.getName().equals(gymUserService.getGymUserById(id).getUsername())) {
                response.put("success", false);
                response.put("message", "No tienes permiso para actualizar los días de asistencia de este usuario");
                return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
            }
            gymUserService.updateAttendanceDays(gymUserService.getGymUserById(id), attendanceDays);
            response.put("success", true);
            response.put("message", "Días de asistencia actualizados con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/apiGymUser/Exercises")
	public ResponseEntity<?> ListExercises() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<ExerciseModel> nutritionPlan = exerciseService.ListExercise();
			response.put("data", nutritionPlan);
			response.put("message", "Ejecicios obtenidos con exito");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}