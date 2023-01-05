package com.diegochueri.people.controllers.dto;

import java.time.LocalDate;

import com.diegochueri.people.models.Person;
import com.diegochueri.people.repositories.PersonRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PersonCreateDto {
	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	private LocalDate birthDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
}
