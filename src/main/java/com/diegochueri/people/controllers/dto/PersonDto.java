package com.diegochueri.people.controllers.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.diegochueri.people.models.Adress;
import com.diegochueri.people.models.Person;

public class PersonDto {
	private Long id;
	private String name;
	private LocalDate birthDate;
	private List<Adress> adress;

	public PersonDto(Person people) {
		this.id = people.getId();
		this.name = people.getName();
		this.birthDate = people.getBirthDate();
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

	public List<Adress> getAdress() {
		return adress;
	}

	public static List<PersonDto> generateDtoList(List<Person> person) {
		return person.stream().map(PersonDto::new).collect(Collectors.toList());
	}
}
