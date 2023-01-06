package com.diegochueri.people.utils;

import java.time.LocalDate;
import java.util.List;

import com.diegochueri.people.controllers.dto.AddressCreateDto;
import com.diegochueri.people.controllers.dto.AddressUpdateDto;
import com.diegochueri.people.models.Address;
import com.diegochueri.people.models.Person;

public class AddressMockCreate {

	Long id = (long) 1;
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

	public Address addressAdd(Person person) {
		Address address = new Address(street, cep, number, town, isMain, null);

		address.setPerson(person);
		address.setId(id);

		return address;
	}

	public Address addressAdd(Person person, String street) {
		Address address = new Address(street, cep, number, town, isMain, null);

		address.setPerson(person);
		address.setId(id);

		return address;
	}

	public AddressCreateDto addressCreateDtoAdd() {
		AddressCreateDto addressCreate = new AddressCreateDto();

		addressCreate.setCep(cep);
		addressCreate.setIsMain(isMain);
		addressCreate.setNumber(number);
		addressCreate.setPersonId((long) 1);
		addressCreate.setStreet(street);
		addressCreate.setTown(town);

		return addressCreate;
	}

	public AddressUpdateDto addressUpdateDtoAdd() {
		AddressUpdateDto addressUpdate = new AddressUpdateDto();

		addressUpdate.setCep(cepUpdated);
		addressUpdate.setIsMain(isMainUpdated);
		addressUpdate.setNumber(numberUpdated);
		addressUpdate.setStreet(streetUpdated);
		addressUpdate.setTown(townUpdated);

		return addressUpdate;
	}

}
