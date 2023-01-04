package com.diegochueri.people.controllers.dto;

import com.diegochueri.people.models.Address;
import com.diegochueri.people.models.Person;
import com.diegochueri.people.repositories.PersonRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AddressCreateDto {
	@NotNull @NotEmpty
	private String street;
	@NotNull @NotEmpty
	private String cep;
	@NotNull @NotEmpty
	private String number;
	@NotNull @NotEmpty
	private String town;
	@NotNull @NotEmpty
	private boolean isMain;
	@NotNull @NotEmpty
	private Long personId;

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

	public Long getPerson() {
		return personId;
	}

	public void setPerson(Long personId) {
		this.personId = personId;
	}

	public Address setAdress(PersonRepository repository) {
		Person person = repository.findById(personId).orElseThrow(IllegalArgumentException::new);
		return new Address(street, cep, number, town, isMain, person);
	}
}
