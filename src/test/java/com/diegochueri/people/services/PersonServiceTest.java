package com.diegochueri.people.services;

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

import com.diegochueri.people.models.Person;
import com.diegochueri.people.repositories.PersonRepository;

@DataJpaTest
public class PersonServiceTest {

	@InjectMocks
	private PersonService service;
	
	@Mock
	private PersonRepository repository;

	private Person person;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		addPersons();
	}

	@Test
	void whenGetOneByIdThenReturnAPerson() {
		Mockito.when(repository.getReferenceById(Mockito.anyLong())).thenReturn(person);
		Person response = service.getOneById((long) 1);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(Person.class, response.getClass());
	}

	private void addPersons() {
		person = new Person();
		person.setName("Person");
		person.setBirthDate(LocalDate.parse("1995-03-16"));
		repository.save(person);
	}
}
