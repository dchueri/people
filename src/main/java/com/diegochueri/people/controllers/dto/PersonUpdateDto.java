package com.diegochueri.people.controllers.dto;

import com.diegochueri.people.models.Person;

public class PersonUpdateDto extends PersonCreateDto {

	public Person updatePerson(Person person, PersonUpdateDto personInfoToUpdate) {
		if (personInfoToUpdate.getName() != null) {
			person.setName(personInfoToUpdate.getName());
		}

		if (personInfoToUpdate.getBirthDate() != null) {
			person.setBirthDate(personInfoToUpdate.getBirthDate());
		}
		return person;
	}
}