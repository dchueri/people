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
	private List<AddressDto> addresses;

	public PersonDetailsDto(Person person) {
		this.id = person.getId();
		this.name = person.getName();
		this.birthDate = person.getBirthDate();
		this.addresses = new ArrayList<>();
		this.addresses.addAll(person.getAddresses().stream().map(AddressDto::new).collect(Collectors.toList()));
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

	public List<AddressDto> getAdresses() {
		return addresses;
	}

}
