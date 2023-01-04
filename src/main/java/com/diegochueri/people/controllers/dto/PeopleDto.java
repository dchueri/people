package com.diegochueri.people.controllers.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.diegochueri.people.models.Adress;
import com.diegochueri.people.models.People;

public class PeopleDto {
	private Long id;
	private String name;
	private LocalDate birthDate;
	private List<Adress> adress;

	public PeopleDto(People people) {
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

	public static List<PeopleDto> generateDtoList(List<People> people) {
		return people.stream().map(PeopleDto::new).collect(Collectors.toList());
	}
}
