package com.diegochueri.people.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Adress {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String street;
	private String cep;
	private String number;
	private String town;
	private boolean isMain;
	@ManyToOne
	private Person person;
	
	public Adress() {
	}

	public Adress(String street, String cep, String number, String town, boolean isMain, Person person) {
		this.street = street;
		this.cep = cep;
		this.number = number;
		this.town = town;
		this.isMain = isMain;
		this.person = person;
	}
	
	public Long getId() {
		return id;
	}

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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
