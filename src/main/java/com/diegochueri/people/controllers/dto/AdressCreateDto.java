package com.diegochueri.people.controllers.dto;

import com.diegochueri.people.models.Adress;
import com.diegochueri.people.models.People;
import com.diegochueri.people.repositories.PeopleRepository;

public class AdressCreateDto {
	private String street;
	private String cep;
	private String number;
	private String town;
	private boolean isMain;
	private Long peopleId;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public boolean isMain() {
		return isMain;
	}

	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}

	public Long getPeople() {
		return peopleId;
	}

	public void setPeople(Long peopleId) {
		this.peopleId = peopleId;
	}

	public Adress setAdress(PeopleRepository repository) {
		People people = repository.findById(peopleId).orElseThrow(IllegalArgumentException::new);
		return new Adress(street, cep, number, town, isMain, people);
	}
}
