package com.diegochueri.people.utils;

import java.time.LocalDate;

import com.diegochueri.people.controllers.dto.PersonCreateDto;
import com.diegochueri.people.controllers.dto.PersonDto;
import com.diegochueri.people.controllers.dto.PersonUpdateDto;
import com.diegochueri.people.models.Person;

public class PersonMockCreate {
	Long id = (long) 1;
	String name = "Person";
	String nameUpdated = "Person Updated";
	LocalDate birthDate = LocalDate.parse("1995-03-16");
	LocalDate birthDateUpdated = LocalDate.parse("1997-12-15");

	public Person personAdd() {
		Person person = new Person();
		person.setId((long) 1);
		person.setName(name);
		person.setBirthDate(birthDate);

		return person;
	}

	public PersonDto personDtoAdd(Person person) {
		PersonDto personDto = new PersonDto(person);
		return personDto;
	}

	public PersonCreateDto personCreateDtoAdd() {
		PersonCreateDto personCreate = new PersonCreateDto();
		personCreate.setName(name);
		personCreate.setBirthDate(birthDate);
		return personCreate;
	}

	public PersonUpdateDto personUpdateDtoAdd() {
		PersonUpdateDto personUpdate = new PersonUpdateDto();
		personUpdate.setName(nameUpdated);
		personUpdate.setBirthDate(birthDateUpdated);
		return personUpdate;
	}
}
