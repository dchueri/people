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
import com.diegochueri.people.controllers.dto.PersonUpdateDto;
import com.diegochueri.people.models.Person;
import com.diegochueri.people.repositories.PersonRepository;
import com.diegochueri.people.utils.PersonMockCreate;

@DataJpaTest
public class PersonServiceTest {
	
	Long id = (long) 1;
	String name = "Person";
	String updatedName = "Person Updated";
	LocalDate birthDate = LocalDate.parse("1995-03-16");
	LocalDate updatedBirthDate = LocalDate.parse("1997-12-15");

	@InjectMocks
	private PersonService service;

	@Mock
	private PersonRepository repository;

	private PersonMockCreate personsMock = new PersonMockCreate();

	private Person person;
	private PersonCreateDto personCreate;
	private PersonUpdateDto personUpdate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		person = personsMock.personAdd();
		personCreate = personsMock.personCreateDtoAdd();
	}

	@Test
	void whenGetAllThenReturnAPersonsList() {
		when(repository.findAll()).thenReturn(List.of(person, person));
		List<Person> response = service.getAll();
		Assertions.assertNotNull(response);
		Assertions.assertEquals(2, response.size());
		Assertions.assertEquals(Person.class, response.get(0).getClass());
	}

	@Test
	void whenGetOneByIdThenReturnAPerson() {
		when(repository.getReferenceById(Mockito.anyLong())).thenReturn(person);
		Person response = service.getOneById(id);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(Person.class, response.getClass());
		Assertions.assertEquals(id, response.getId());
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

	@Test
	void whenUpdateNameThenReturnAPersonWithOnlyNameUpdated() {
		personUpdate = new PersonUpdateDto();
		personUpdate.setName(updatedName);

		Person response = service.update(person, personUpdate);

		Assertions.assertEquals(Person.class, response.getClass());
		Assertions.assertNotEquals(name, response.getName());
		Assertions.assertEquals(updatedName, response.getName());
		Assertions.assertEquals(birthDate, response.getBirthDate());
	}

	@Test
	void whenUpdateBirthDateThenReturnAPersonWithOnlyBirthDateUpdated() {
		personUpdate = new PersonUpdateDto();
		personUpdate.setBirthDate(updatedBirthDate);

		Person response = service.update(person, personUpdate);

		Assertions.assertEquals(Person.class, response.getClass());
		Assertions.assertNotEquals(birthDate, response.getBirthDate());
		Assertions.assertEquals(name, response.getName());
		Assertions.assertEquals(updatedBirthDate, response.getBirthDate());
	}
}
