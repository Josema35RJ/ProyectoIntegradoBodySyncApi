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
import com.example.demo.service.GymClassService;
import com.example.demo.service.GymUserService;

@RestController
public class RestApiController {

	@Autowired
	@Qualifier("gymUserService")
	private GymUserService gymUserService;
	
	@Autowired
    @Qualifier("gymClassService")
    private GymClassService gymClassService;

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

			List<GymClass> clases = gymClassService.getAllClassesFinByInstructorId(gymUserService.getGymUserById(id));// Obtener los servicios de la
																						
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
}