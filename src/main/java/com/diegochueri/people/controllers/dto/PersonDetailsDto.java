package com.diegochueri.people.controllers.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.diegochueri.people.models.Person;

public class PersonDetailsDto {
	private Long id;
	private String name;
	private LocalDate birthDate;
	private List<AdressDto> adresses;

	public PersonDetailsDto(Person person) {
		this.id = person.getId();
		this.name = person.getName();
		this.birthDate = person.getBirthDate();
		this.adresses = new ArrayList<>();
		this.adresses.addAll(person.getAddresses().stream().map(AdressDto::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public List<AdressDto> getAdresses() {
		return adresses;
	}

}
