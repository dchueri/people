package com.diegochueri.people.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegochueri.people.controllers.dto.PersonCreateDto;
import com.diegochueri.people.controllers.dto.PersonUpdateDto;
import com.diegochueri.people.models.Person;
import com.diegochueri.people.repositories.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;

	public List<Person> getAll() {
		List<Person> persons = repository.findAll();
		return persons;
	}

	public Person add(PersonCreateDto personCreate) {
		Person person = new Person(personCreate);
		repository.save(person);
		return person;
	}

	public Person getOneById(Long id) {
		Person person = repository.getReferenceById(id);
		return person;
	}
	
	public Person update(Person person, PersonUpdateDto personInfoToUpdate) {
		if (personInfoToUpdate.getName() != null) {
			person.setName(personInfoToUpdate.getName());
		}

		if (personInfoToUpdate.getBirthDate() != null) {
			person.setBirthDate(personInfoToUpdate.getBirthDate());
		}
		return person;
	}
}
