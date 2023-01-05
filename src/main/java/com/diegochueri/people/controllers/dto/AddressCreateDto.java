package com.diegochueri.people.controllers.dto;

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
	@NotNull
	private boolean isMain;
	@NotNull
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

	public boolean getIsMain() {
		return isMain;
	}

	public void setIsMain(boolean isMain) {
		this.isMain = isMain;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}
}
