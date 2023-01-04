package com.diegochueri.people.controllers.dto;

import java.time.LocalDate;

import com.diegochueri.people.models.Adress;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PersonCreateDto {
	@NotNull @NotEmpty
	private String name;
	@NotNull
	private LocalDate birthDate;
	private Adress adress;
	
	public Adress getAdress() {
		return adress;
	}
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
	
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
