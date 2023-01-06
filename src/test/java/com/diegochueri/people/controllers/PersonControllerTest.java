package com.diegochueri.people.controllers;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.diegochueri.people.controllers.dto.PersonDto;
import com.diegochueri.people.models.Person;
import com.diegochueri.people.services.PersonService;
import com.diegochueri.people.utils.PersonMockCreate;

@SpringBootTest
class PersonControllerTest {

	Long id = (long) 1;
	String name = "Person";
	String updatedName = "Person Updated";
	LocalDate birthDate = LocalDate.parse("1995-03-16");
	LocalDate updatedBirthDate = LocalDate.parse("1997-12-15");

	@InjectMocks
	private PersonController controller;

	@Mock
	private PersonService service;

	@Mock
	private PersonDto personDtoMock;

	private PersonMockCreate personMockCreate = new PersonMockCreate();

	private Person person;

	private PersonDto personDto;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		person = personMockCreate.personAdd();
		personDto = personMockCreate.personDtoAdd(person);
	}

	@Test
	void WhenListAllPersonsThenReturnAPersonsListDto() {
		List<Person> personsList = List.of(person, person);
		List<PersonDto> personsDtoList = List.of(personDto, personDto);
		when(service.getAll()).thenReturn(personsList);
		when(personDtoMock.generateDtoList(Mockito.any())).thenReturn(personsDtoList);

		ResponseEntity<List<PersonDto>> response = controller.listAllPersons();

		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(ArrayList.class, response.getBody().getClass());
		Assertions.assertEquals(PersonDto.class, response.getBody().get(0).getClass());
	}
}
