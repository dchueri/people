package com.diegochueri.people.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.diegochueri.people.controllers.dto.AddressCreateDto;
import com.diegochueri.people.controllers.dto.AddressDto;
import com.diegochueri.people.controllers.dto.AddressUpdateDto;
import com.diegochueri.people.controllers.dto.PersonCreateDto;
import com.diegochueri.people.controllers.dto.PersonDetailsDto;
import com.diegochueri.people.controllers.dto.PersonDto;
import com.diegochueri.people.controllers.dto.PersonUpdateDto;
import com.diegochueri.people.models.Address;
import com.diegochueri.people.models.Person;
import com.diegochueri.people.repositories.AddressRepository;
import com.diegochueri.people.repositories.PersonRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AddressRepository addressRepository;

	@GetMapping
	public List<PersonDto> listAllPersons() {
		List<Person> person = personRepository.findAll();
		return PersonDto.generateDtoList(person);
	}

	@PostMapping
	public ResponseEntity<PersonDto> registerPerson(@RequestBody @Valid PersonCreateDto personCreate,
			UriComponentsBuilder uriBuilder) {
		Person person = new Person(personCreate);
		personRepository.save(person);
		URI uri = uriBuilder.path("/person/{id}").buildAndExpand(person.getId()).toUri();
		return ResponseEntity.created(uri).body(new PersonDto(person));
	}

	@GetMapping("/{id}")
	public PersonDetailsDto listOnePerson(@PathVariable Long id) {
		Person person = personRepository.getReferenceById(id);
		return new PersonDetailsDto(person);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PersonDto> updatePerson(@PathVariable Long id, @RequestBody PersonUpdateDto personUpdate) {
		Person person = personRepository.getReferenceById(id);
		Person personUpdated = personUpdate.updatePerson(person, personUpdate);
		return ResponseEntity.ok(new PersonDto(personUpdated));
	}

	@PostMapping("/{id}/addresses")
	public ResponseEntity<AddressDto> registerAddress(@PathVariable Long id,
			@RequestBody @Valid AddressCreateDto addressCreate, UriComponentsBuilder uriBuilder) {
		Person person = personRepository.getReferenceById(id);
		Address address = new Address(addressCreate, person);
		addressRepository.save(address);
		URI uri = uriBuilder.path("/person/{id}/addresses").buildAndExpand(address.getId()).toUri();
		return ResponseEntity.created(uri).body(new AddressDto(address));
	}

	@PutMapping("/{id}/addresses/{addressId}")
	@Transactional
	public ResponseEntity<AddressDto> updatePerson(@PathVariable Long id, @PathVariable Long addressId,
			@RequestBody AddressUpdateDto addressUpdate) {
		Address address = addressRepository.getReferenceById(id);
		Address addressUpdated = addressUpdate.updateAddress(address, addressUpdate);
		return ResponseEntity.ok(new AddressDto(addressUpdated));
	}
}
