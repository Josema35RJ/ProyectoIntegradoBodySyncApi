package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

//La clase NutritionPlan representa un plan de nutrición personalizado para un usuario.
@Entity
@Data
@Table(name = "nutrition_plan")
public class NutritionPlan {
	// Identificador único para cada plan de nutrición.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	// Usuario que ha hecho el registro de entrenamiento.
	@ManyToOne
	@NotNull(message = "GymUser is required")
	private GymUser gymUser;

	// Nombre del plan de nutrición.
	@NotBlank(message = "Name is required")
	private String name;

	// Descripción del plan de nutrición.
	@NotBlank(message = "Description is required")
	private String description;

	// Fecha de creación del plan de nutrición.
	private LocalDateTime createdAt;

	// Fecha de última modificación del plan de nutrición.
	private LocalDateTime updatedAt;
}
