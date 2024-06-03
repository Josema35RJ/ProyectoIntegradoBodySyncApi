package com.example.demo.model;

import java.time.LocalDateTime;

import com.example.demo.entity.GymUser;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MealLogModel {
	// Identificador único para cada registro de comida.
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;

		// Usuario que ha hecho el registro de comida.
		@ManyToOne
		@NotNull(message = "GymUser is required")
		private GymUser gymUser;

		// Fecha de la comida.
		private LocalDateTime mealDate;

		// Descripción de la comida.
		@NotBlank(message = "Meal description is required")
		private String mealDescription;

		// Calorías consumidas en la comida.
		@Min(value = 0, message = "Calories consumed must be a non-negative number")
		private Integer caloriesConsumed;
}
