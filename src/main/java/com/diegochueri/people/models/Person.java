package com.diegochueri.people.models;

import java.time.LocalDate;
import java.util.List;

import com.diegochueri.people.controllers.dto.PersonCreateDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private LocalDate birthDate;
	@OneToMany(mappedBy = "person")
	private List<Address> addresses;

	public Person() {
	}

	public Person(PersonCreateDto person) {
		this.name = person.getName();
		this.birthDate = person.getBirthDate();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAdresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}
