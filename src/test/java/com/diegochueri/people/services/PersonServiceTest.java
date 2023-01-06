package com.diegochueri.people.services;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.diegochueri.people.controllers.dto.PersonCreateDto;
import com.diegochueri.people.models.Person;
import com.diegochueri.people.repositories.PersonRepository;

@DataJpaTest
public class PersonServiceTest {

	String name = "Person";
	LocalDate birthDate = LocalDate.parse("1995-03-16");

	@InjectMocks
	private PersonService service;

	@Mock
	private PersonRepository repository;

	private Person person;
	private PersonCreateDto personCreate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		addPerson();
	}

	@Test
	void whenGetOneByIdThenReturnAPerson() {
		when(repository.getReferenceById(Mockito.anyLong())).thenReturn(person);
		Person response = service.getOneById((long) 1);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(Person.class, response.getClass());
	}

	@Test
	void whenFindAllThenReturnAPersonsList() {
		when(repository.findAll()).thenReturn(List.of(person, person));
		List<Person> response = service.getAll();
		Assertions.assertNotNull(response);
		Assertions.assertEquals(2, response.size());
		Assertions.assertEquals(Person.class, response.get(0).getClass());
	}

	@Test
	void whenAddThenReturnACreatedPerson() {
		when(repository.save(Mockito.any())).thenReturn(person);
		
		Person response = service.add(personCreate);
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(Person.class, response.getClass());
		Assertions.assertEquals(name, response.getName());		
		Assertions.assertEquals(birthDate, response.getBirthDate());		
	}

	private void addPerson() {
		person = new Person();
		person.setName(name);
		person.setBirthDate(birthDate);

		personCreate = new PersonCreateDto();
		personCreate.setName(name);
		personCreate.setBirthDate(birthDate);
	}
}
