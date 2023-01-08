package com.diegochueri.people.services;

import static org.mockito.Mockito.mockitoSession;
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
import org.springframework.boot.test.context.SpringBootTest;

import com.diegochueri.people.controllers.dto.AddressCreateDto;
import com.diegochueri.people.controllers.dto.AddressUpdateDto;
import com.diegochueri.people.controllers.dto.PersonUpdateDto;
import com.diegochueri.people.models.Address;
import com.diegochueri.people.models.Person;
import com.diegochueri.people.repositories.AddressRepository;
import com.diegochueri.people.utils.AddressMockCreate;
import com.diegochueri.people.utils.PersonMockCreate;
import com.diegochueri.people.validations.InputsValidate;
import com.diegochueri.people.validations.dto.UpdateDataNotInformedException;

@SpringBootTest
public class AddressServiceTest {

	String street = "Rua Teste";
	String streetUpdated = "Rua Teste Updated";
	String cep = "12345678";
	String cepUpdated = "12345679";
	String number = "123";
	String numberUpdated = "321";
	String town = "Cidade-BR";
	String townUpdated = "Updated-BR";
	boolean isMain = true;
	boolean isMainUpdated = false;

	Long id = (long) 1;
	String name = "Person";
	LocalDate birthDate = LocalDate.parse("1995-03-16");

	@InjectMocks
	private AddressService service;

	@Mock
	private AddressService serviceMock;

	@Mock
	private AddressRepository repository;
	
	@Mock
	private InputsValidate inputsValidate;

	private PersonMockCreate personMockCreate = new PersonMockCreate();
	private AddressMockCreate addressMockCreate = new AddressMockCreate();

	private Person person;
	private Address address;
	private List<Address> addressList;
	private AddressCreateDto addressCreate;
	private AddressUpdateDto addressUpdate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		person = personMockCreate.personAdd();

		address = addressMockCreate.addressAdd(person);
		Address notMainAddress = new Address(town, cep, number, street, false, person);
		addressList = List.of(address, notMainAddress);
		person.setAdresses(addressList);
		addressCreate = addressMockCreate.addressCreateDtoAdd();
		addressUpdate = addressMockCreate.addressUpdateDtoAdd();
	}

	@Test
	void whenGetAllOfPersonThenAllAddressesOfPerson() {
		when(repository.findAllByPersonId(Mockito.anyLong())).thenReturn(addressList);
		List<Address> response = service.getAllOfPerson(id);
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(2, response.size());
		Assertions.assertEquals(Address.class, response.get(0).getClass());
	}

	@Test
	void whenGetOneByIdThenReturnAnAddress() {
		when(repository.getReferenceById(Mockito.anyLong())).thenReturn(address);
		Address response = service.getOneById((long) 1);
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(Address.class, response.getClass());
		Assertions.assertEquals(id, response.getId());
		Assertions.assertEquals(town, response.getTown());
		Assertions.assertEquals(cep, response.getCep());
		Assertions.assertEquals(number, response.getNumber());
		Assertions.assertEquals(street, response.getStreet());
		Assertions.assertEquals(isMain, response.getIsMain());
		Assertions.assertEquals(person, response.getPerson());
	}

	@Test
	void whenAddThenReturnACreatedAddress() {
		Address addressCreated = new Address(street, cep, number, town, isMain, person);
		when(repository.save(Mockito.any())).thenReturn(addressCreated);
		Address response = service.add(addressCreate, person);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(Address.class, response.getClass());
		Assertions.assertEquals(town, response.getTown());
		Assertions.assertEquals(cep, response.getCep());
		Assertions.assertEquals(number, response.getNumber());
		Assertions.assertEquals(street, response.getStreet());
		Assertions.assertEquals(isMain, response.getIsMain());
	}

	@Test
	void whenUpdateStreetThenReturnAnAddressWithOnlyStreetUpdated() {
		Address addressToUpdate = new Address(street, cep, number, town, isMain, person);
		addressUpdate = new AddressUpdateDto();
		addressUpdate.setStreet(streetUpdated);

		Address response = service.update(addressToUpdate, addressUpdate);
		Assertions.assertEquals(Address.class, response.getClass());
		Assertions.assertNotEquals(street, response.getStreet());
		Assertions.assertEquals(streetUpdated, response.getStreet());
		Assertions.assertEquals(town, response.getTown());
		Assertions.assertEquals(cep, response.getCep());
		Assertions.assertEquals(number, response.getNumber());
	}

	@Test
	void whenUpdateCepThenReturnAnAddressWithOnlyCepUpdated() {
		Address addressToUpdate = new Address(street, cep, number, town, isMain, person);
		addressUpdate = new AddressUpdateDto();
		addressUpdate.setCep(cepUpdated);

		Address response = service.update(addressToUpdate, addressUpdate);
		Assertions.assertEquals(Address.class, response.getClass());
		Assertions.assertNotEquals(cep, response.getCep());
		Assertions.assertEquals(cepUpdated, response.getCep());
		Assertions.assertEquals(town, response.getTown());
		Assertions.assertEquals(street, response.getStreet());
		Assertions.assertEquals(number, response.getNumber());
	}

	@Test
	void whenUpdateNumberThenReturnAnAddressWithOnlyNumberUpdated() {
		Address addressToUpdate = new Address(street, cep, number, town, isMain, person);
		addressUpdate = new AddressUpdateDto();
		addressUpdate.setNumber(numberUpdated);

		Address response = service.update(addressToUpdate, addressUpdate);
		Assertions.assertEquals(Address.class, response.getClass());
		Assertions.assertNotEquals(number, response.getNumber());
		Assertions.assertEquals(numberUpdated, response.getNumber());
		Assertions.assertEquals(town, response.getTown());
		Assertions.assertEquals(street, response.getStreet());
		Assertions.assertEquals(cep, response.getCep());
	}

	@Test
	void whenUpdateTownThenReturnAnAddressWithOnlyTownUpdated() {
		Address addressToUpdate = new Address(street, cep, number, town, isMain, person);
		addressUpdate = new AddressUpdateDto();
		addressUpdate.setTown(townUpdated);

		Address response = service.update(addressToUpdate, addressUpdate);
		Assertions.assertEquals(Address.class, response.getClass());
		Assertions.assertNotEquals(town, response.getTown());
		Assertions.assertEquals(townUpdated, response.getTown());
		Assertions.assertEquals(number, response.getNumber());
		Assertions.assertEquals(street, response.getStreet());
		Assertions.assertEquals(cep, response.getCep());
	}

	@Test
	void whenUpdateIsMainThenReturnAnAddressWithOnlyIsMainUpdated() {
		Address addressToUpdate = new Address(street, cep, number, town, isMainUpdated, person);
		addressUpdate = new AddressUpdateDto();
		addressUpdate.setIsMain(isMain);

		Address response = service.update(addressToUpdate, addressUpdate);
		Assertions.assertEquals(Address.class, response.getClass());
		Assertions.assertNotEquals(isMainUpdated, response.getIsMain());
		Assertions.assertEquals(isMain, response.getIsMain());
		Assertions.assertEquals(number, response.getNumber());
		Assertions.assertEquals(street, response.getStreet());
		Assertions.assertEquals(cep, response.getCep());
		Assertions.assertEquals(town, response.getTown());
	}

	@Test
	void whenAnAddressIsDefinedAsMainThenRemoveAnotherMainOfAddress() {
		Address addressToUpdate = new Address(street, cep, number, town, isMainUpdated, person);
		addressUpdate = new AddressUpdateDto();
		addressUpdate.setIsMain(isMain);

		Address response = service.update(addressToUpdate, addressUpdate);
		Assertions.assertEquals(Address.class, response.getClass());
		Assertions.assertEquals(isMain, response.getIsMain());
		Assertions.assertEquals(isMainUpdated, person.getAddresses().get(0).getIsMain());
	}
}
