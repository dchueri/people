package com.diegochueri.people.controllers;

import static org.mockito.Mockito.when;

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

import com.diegochueri.people.controllers.dto.AddressCreateDto;
import com.diegochueri.people.controllers.dto.AddressDto;
import com.diegochueri.people.controllers.dto.AddressUpdateDto;
import com.diegochueri.people.controllers.dto.PersonCreateDto;
import com.diegochueri.people.controllers.dto.PersonDetailsDto;
import com.diegochueri.people.controllers.dto.PersonDto;
import com.diegochueri.people.controllers.dto.PersonUpdateDto;
import com.diegochueri.people.models.Address;
import com.diegochueri.people.models.Person;
import com.diegochueri.people.services.AddressService;
import com.diegochueri.people.services.PersonService;
import com.diegochueri.people.utils.AddressMockCreate;
import com.diegochueri.people.utils.PersonMockCreate;

@SpringBootTest
class PersonControllerTest {

	@InjectMocks
	private PersonController controller;

	@InjectMocks
	private PersonService personService;

	@InjectMocks
	private AddressService addressService;

	@Mock
	private PersonService personServiceMock;

	@Mock
	private AddressService addressServiceMock;

	@Mock
	private PersonDto personDtoMock;

	private PersonMockCreate personMockCreate = new PersonMockCreate();
	private AddressMockCreate addressMockCreate = new AddressMockCreate();

	private Person person;
	private PersonDto personDto;
	private PersonCreateDto personCreateDto;
	private PersonUpdateDto personUpdateDto;

	private Address address;
	private AddressCreateDto addressCreateDto;
	private AddressUpdateDto addressUpdateDto;
	private List<Address> addressesList;

	Long id = (long) 1;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		person = personMockCreate.personAdd();
		personDto = personMockCreate.personDtoAdd(person);
		personCreateDto = personMockCreate.personCreateDtoAdd();
		address = addressMockCreate.addressAdd(person);
		addressCreateDto = addressMockCreate.addressCreateDtoAdd();
		addressesList = List.of(address, addressMockCreate.addressAdd(person, "Rua Teste 1"));
		person.setAdresses(addressesList);
	}

	@Test
	void whenListAllPersonsThenReturnAPersonsListDto() {
		List<Person> personsList = List.of(person, person);
		List<PersonDto> personsDtoList = List.of(personDto, personDto);
		when(personServiceMock.getAll()).thenReturn(personsList);
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
		when(personServiceMock.add(Mockito.any())).thenReturn(person);
		
		ResponseEntity<PersonDto> response = controller.registerPerson(personCreateDto);
		
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertEquals(PersonDto.class, response.getBody().getClass());
		Assertions.assertEquals(id, response.getBody().getId());
		Assertions.assertEquals(personCreateDto.getName(), response.getBody().getName());
		Assertions.assertEquals(personCreateDto.getBirthDate(), response.getBody().getBirthDate());	
	}

	@Test
	void whenListOnePersonThenReturnAPerson() {
		when(personServiceMock.getOneById(Mockito.any())).thenReturn(person);
		
		ResponseEntity<PersonDetailsDto> response = controller.listOnePerson((long) 1);
		
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(PersonDetailsDto.class, response.getBody().getClass());		
		Assertions.assertEquals(id, response.getBody().getId());
		Assertions.assertEquals(person.getBirthDate(), response.getBody().getBirthDate());
		Assertions.assertEquals(person.getName(), response.getBody().getName());
		Assertions.assertEquals(addressesList.get(0).getStreet(), response.getBody().getAdresses().get(0).getStreet());
		Assertions.assertNotEquals(addressesList.get(0).getStreet(), response.getBody().getAdresses().get(1).getStreet());
		Assertions.assertEquals(addressesList.get(1).getStreet(), response.getBody().getAdresses().get(1).getStreet());
	}

	@Test
	void whenUpdateNameThenReturnAPersonWithOnlyNameUpdated() {
		personUpdateDto = new PersonUpdateDto();
		personUpdateDto.setName("Updated Name");

		when(personServiceMock.getOneById(Mockito.any())).thenReturn(person);
		Person personUpdated = personService.update(person, personUpdateDto);
		when(personServiceMock.update(Mockito.any(), Mockito.any())).thenReturn(personUpdated);

		ResponseEntity<PersonDto> response = controller.updatePerson(id, personUpdateDto);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(PersonDto.class, response.getBody().getClass());
		Assertions.assertEquals(personUpdateDto.getName(), response.getBody().getName());
		Assertions.assertEquals(person.getBirthDate(), response.getBody().getBirthDate());
	}

	@Test
	void whenRegisterAddressThenReturnACreatedAddress() {
		when(personServiceMock.getOneById(Mockito.any())).thenReturn(person);
		when(addressServiceMock.add(addressCreateDto, person)).thenReturn(address);
		
		ResponseEntity<AddressDto> response = controller.registerAddress(id, addressCreateDto);
		
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(AddressDto.class, response.getBody().getClass());
		
		Assertions.assertEquals(id, response.getBody().getId());
		Assertions.assertEquals(id, addressCreateDto.getPersonId());		
		Assertions.assertEquals(addressCreateDto.getCep(), response.getBody().getCep());
		Assertions.assertEquals(addressCreateDto.getNumber(), response.getBody().getNumber());
		Assertions.assertEquals(addressCreateDto.getTown(), response.getBody().getTown());
		Assertions.assertEquals(addressCreateDto.getStreet(), response.getBody().getStreet());
		Assertions.assertEquals(addressCreateDto.getIsMain(), response.getBody().getIsMain());
	}

	@Test
	void whenUpdateStreetThenReturnAAddressWithOnlyStreetUpdated() {
		addressUpdateDto = new AddressUpdateDto();
		addressUpdateDto.setStreet("Updated Street");
		String lastStreet = address.getStreet();

		when(addressServiceMock.getOneById(Mockito.any())).thenReturn(address);
		Address addressUpdated = addressService.update(address, addressUpdateDto);
		when(addressServiceMock.update(Mockito.any(), Mockito.any())).thenReturn(addressUpdated);

		ResponseEntity<AddressDto> response = controller.updateAddress(id, id, addressUpdateDto);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(AddressDto.class, response.getBody().getClass());
		Assertions.assertNotEquals(lastStreet, response.getBody().getStreet());
		Assertions.assertEquals(addressUpdateDto.getStreet(), response.getBody().getStreet());
		Assertions.assertEquals(address.getTown(), response.getBody().getTown());
		Assertions.assertEquals(address.getCep(), response.getBody().getCep());
		Assertions.assertEquals(address.getNumber(), response.getBody().getNumber());
	}
}
