package com.diegochueri.people.controllers.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.diegochueri.people.models.Person;

public class PersonDto {
	private Long id;
	private String name;
	private LocalDate birthDate;

	public PersonDto() {
	}

	public PersonDto(Person person) {
		this.id = person.getId();
		this.name = person.getName();
		this.birthDate = person.getBirthDate();
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

	public List<PersonDto> generateDtoList(List<Person> personList) {
		return personList.stream().map(PersonDto::new).collect(Collectors.toList());
	}
}
