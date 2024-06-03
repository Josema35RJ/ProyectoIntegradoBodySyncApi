package com.example.demo.model;

import java.time.LocalDateTime;

import com.example.demo.entity.GymUser;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AchievementModel {
	// Identificador único para cada logro.
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;

		// Usuario que ha conseguido el logro.
		@ManyToOne
		@NotNull(message = "GymUser is required")
		private GymUserModel gymUser;

		// Nombre del logro.
		@NotBlank(message = "Name is required")
		private String name;

		// Descripción del logro.
		@NotBlank(message = "Description is required")
		private String description;

		// Fecha en la que se consiguió el logro.
		private LocalDateTime achievedAt;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public @NotNull(message = "GymUser is required") GymUserModel getGymUser() {
			return gymUser;
		}

		public void setGymUser(GymUserModel gymUserModel) {
			this.gymUser = gymUserModel;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public LocalDateTime getAchievedAt() {
			return achievedAt;
		}

		public void setAchievedAt(LocalDateTime achievedAt) {
			this.achievedAt = achievedAt;
		}

		@Override
		public String toString() {
			return "AchievementModel [id=" + id + ", gymUser=" + gymUser + ", name=" + name + ", description="
					+ description + ", achievedAt=" + achievedAt + "]";
		}
}
