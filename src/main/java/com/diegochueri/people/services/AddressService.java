package com.diegochueri.people.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegochueri.people.controllers.dto.AddressCreateDto;
import com.diegochueri.people.controllers.dto.AddressUpdateDto;
import com.diegochueri.people.models.Address;
import com.diegochueri.people.models.Person;
import com.diegochueri.people.repositories.AddressRepository;
import com.diegochueri.people.validations.InputsValidate;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;

	public List<Address> getAllOfPerson(Long id) {
		List<Address> addressList = repository.findAllByPersonId(id);
		return addressList;
	}

	public Address getOneById(Long id) {
		Address address = repository.getReferenceById(id);
		return address;
	}

	public Address add(AddressCreateDto addressCreate, Person person) {
		Address address = new Address(addressCreate, person);
		if (address.getIsMain()) {
			this.removeMainAddress(person);
		}
		return repository.save(address);
	}

	public Address update(Address address, AddressUpdateDto addressInfoToUpdate) {
		if (addressInfoToUpdate.getIsMain() == false) {
			InputsValidate.validateIfAddressUpdateInputIsNull(addressInfoToUpdate);
		}
		if (addressInfoToUpdate.getStreet() != null) {
			address.setStreet(addressInfoToUpdate.getStreet());
		}
		if (addressInfoToUpdate.getCep() != null) {
			address.setCep(addressInfoToUpdate.getCep());
		}
		if (addressInfoToUpdate.getNumber() != null) {
			address.setNumber(addressInfoToUpdate.getNumber());
		}
		if (addressInfoToUpdate.getTown() != null) {
			address.setTown(addressInfoToUpdate.getTown());
		}
		if (addressInfoToUpdate.getIsMain() != address.getIsMain()) {
			if (addressInfoToUpdate.getIsMain() == true) {
				this.removeMainAddress(address.getPerson());
			}
			address.setIsMain(addressInfoToUpdate.getIsMain());
		}

		return address;
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

	public void removeMainAddress(Person person) {
		List<Address> addresses = person.getAddresses();
		addresses.forEach(adress -> {
			if (adress.getIsMain() == true) {
				adress.setIsMain(false);
			}
		});
	}
}
