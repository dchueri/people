package com.diegochueri.people.controllers.dto;

import com.diegochueri.people.models.Address;

public class AddressDto {
	private Long id;
	private String street;
	private String cep;
	private String number;
	private String town;
	private boolean isMain;
	
	public AddressDto(Address adress) {
		this.id = adress.getId();
		this.street = adress.getStreet();
		this.cep = adress.getCep();
		this.number = adress.getNumber();
		this.town = adress.getTown();
		this.isMain = adress.getIsMain();
	}

	public Long getId() {
		return id;
	}

	public String getStreet() {
		return street;
	}

	public String getCep() {
		return cep;
	}

	public String getNumber() {
		return number;
	}

	public String getTown() {
		return town;
	}

	public boolean isMain() {
		return isMain;
	}
	
	
}
