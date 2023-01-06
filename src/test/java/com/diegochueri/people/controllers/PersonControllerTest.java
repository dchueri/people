package com.diegochueri.people.controllers;

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

import com.diegochueri.people.controllers.dto.PersonCreateDto;
import com.diegochueri.people.controllers.dto.PersonDetailsDto;
import com.diegochueri.people.controllers.dto.PersonDto;
import com.diegochueri.people.models.Address;
import com.diegochueri.people.models.Person;
import com.diegochueri.people.services.PersonService;
import com.diegochueri.people.utils.AddressMockCreate;
import com.diegochueri.people.utils.PersonMockCreate;

@SpringBootTest
class PersonControllerTest {

	@InjectMocks
	private PersonController controller;

	@Mock
	private PersonService service;

	@Mock
	private PersonDto personDtoMock;

	private PersonMockCreate personMockCreate = new PersonMockCreate();
	private AddressMockCreate addressMockCreate = new AddressMockCreate();

	private Person person;
	private PersonDto personDto;
	private PersonCreateDto personCreateDto;
	private List<Address> addressesList;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		person = personMockCreate.personAdd();
		personDto = personMockCreate.personDtoAdd(person);
		personCreateDto = personMockCreate.personCreateDtoAdd();
		addressesList = List.of(addressMockCreate.addressAdd(person),
				addressMockCreate.addressAdd(person, "Rua Teste 1"));
		person.setAdresses(addressesList);
	}

	@Test
	void whenListAllPersonsThenReturnAPersonsListDto() {
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
		Assertions.assertEquals(2, response.getBody().size());
	}

	@Test
	void whenRegisterPersonThenReturnACreatedPerson() {
		when(service.add(Mockito.any())).thenReturn(person);
		
		ResponseEntity<PersonDto> response = controller.registerPerson(personCreateDto);
		
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertEquals(PersonDto.class, response.getBody().getClass());
		Assertions.assertEquals((long) 1, response.getBody().getId());
		Assertions.assertEquals(personCreateDto.getName(), response.getBody().getName());
		Assertions.assertEquals(personCreateDto.getBirthDate(), response.getBody().getBirthDate());	
	}

	@Test
	void whenListOnePersonThenReturnAPerson() {
		when(service.getOneById(Mockito.any())).thenReturn(person);
		
		ResponseEntity<PersonDetailsDto> response = controller.listOnePerson((long) 1);
		
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(PersonDetailsDto.class, response.getBody().getClass());		
		Assertions.assertEquals((long) 1, response.getBody().getId());
		Assertions.assertEquals(person.getBirthDate(), response.getBody().getBirthDate());
		Assertions.assertEquals(person.getName(), response.getBody().getName());
		Assertions.assertEquals(addressesList.get(0).getStreet(), response.getBody().getAdresses().get(0).getStreet());
		Assertions.assertNotEquals(addressesList.get(0).getStreet(), response.getBody().getAdresses().get(1).getStreet());
		Assertions.assertEquals(addressesList.get(1).getStreet(), response.getBody().getAdresses().get(1).getStreet());
	}
}
