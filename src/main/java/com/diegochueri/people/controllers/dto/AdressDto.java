package com.diegochueri.people.controllers.dto;

import com.diegochueri.people.models.Adress;

public class AdressDto {
	private Long id;
	private String street;
	private String cep;
	private String number;
	private String town;
	private boolean isMain;
	
	public AdressDto(Adress adress) {
		this.id = adress.getId();
		this.street = adress.getStreet();
		this.cep = adress.getCep();
		this.number = adress.getNumber();
		this.town = adress.getTown();
		this.isMain = adress.getIsMain();
	}
	
	
}
