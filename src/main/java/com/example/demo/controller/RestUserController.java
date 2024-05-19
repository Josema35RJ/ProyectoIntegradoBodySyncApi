package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.GymUser;
import com.example.demo.model.GymUserModel;
import com.example.demo.service.GymUserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController

public class RestUserController {
    
	@Autowired
	private GymUserService gymUserService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam("username") String username, @RequestParam("password") String password) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        GymUser gymUser = gymUserService.findGymUserByUsernameAndPassword(username, password);

	        // Generar el token JWT
	        String token = getJWTToken(username, password);
	        gymUser.setToken(token);
             
	        // Crear un mapa con los campos específicos que deseas incluir en la respuesta
	        Map<String, Object> userData = new HashMap<>();
	        userData.put("token", gymUser.getToken());
	        userData.put("id", gymUser.getId());
	        userData.put("ROL", gymUser.getRole()); 
	        
	        response.put("success", true);
	        response.put("data", userData);
	        response.put("message", "Inicio de sesion exitoso");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (DisabledException e) {
	        response.put("success", false);
	        response.put("message", "El usuario no esta activado");
	        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	    } catch (AccountExpiredException e) {
	        response.put("success", false);
	        response.put("message", "El usuario ha sido borrado");
	        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	    } catch (BadCredentialsException e) {
	        response.put("success", false);
	        response.put("message", "Usuario o clave incorrectos");
	        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "Error al iniciar sesion");
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody GymUser gymUser){
	    Map<String, Object> response = new HashMap<>();
	    try {
	         gymUserService.registrar(gymUser);
	        response.put("success", true);
	        response.put("message", "Usuario registrado con exito");
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    } catch (IllegalArgumentException e) {
	        response.put("success", false);
	        response.put("message", e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "Error al registrar el usuario: " + e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	private String getJWTToken(String username, String password) {
	    String secretKey = "mySecretKey";
	    GymUser gymUser = gymUserService.findGymUserByUsernameAndPassword(username, password);
	    List<GrantedAuthority> grantedAuthorities = AuthorityUtils
	            .commaSeparatedStringToAuthorityList(gymUser.getRole());
	    
	    String token = Jwts
	            .builder()
	            .setId("softtekJWT")
	            .setSubject(username)
	            .claim("authorities",
	                    grantedAuthorities.stream()
	                            .map(GrantedAuthority::getAuthority)
	                            .collect(Collectors.toList()))
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + 600000))
	            .signWith(SignatureAlgorithm.HS512,
	                    secretKey.getBytes()).compact();
	    return "Bearer " + token;
	}

}
