package com.diegochueri.people.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.diegochueri.people.controllers.dto.AddressCreateDto;
import com.diegochueri.people.controllers.dto.AddressDto;
import com.diegochueri.people.controllers.dto.PersonCreateDto;
import com.diegochueri.people.controllers.dto.PersonDetailsDto;
import com.diegochueri.people.controllers.dto.PersonDto;
import com.diegochueri.people.models.Address;
import com.diegochueri.people.models.Person;
import com.diegochueri.people.repositories.AddressRepository;
import com.diegochueri.people.repositories.PersonRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AddressRepository addressRepository;

	@GetMapping
	public List<PersonDto> listAll() {
		List<Person> person = personRepository.findAll();
		return PersonDto.generateDtoList(person);
	}

	@PostMapping
	public ResponseEntity<PersonDto> register(@RequestBody @Valid PersonCreateDto personCreate,
			UriComponentsBuilder uriBuilder) {
		Person person = new Person(personCreate);
		personRepository.save(person);
		URI uri = uriBuilder.path("/person/{id}").buildAndExpand(person.getId()).toUri();
		return ResponseEntity.created(uri).body(new PersonDto(person));
	}

	@GetMapping("/{id}")
	public PersonDetailsDto listOne(@PathVariable Long id) {
		Person person = personRepository.getReferenceById(id);
		return new PersonDetailsDto(person);
	}

	@PostMapping("/{id}/addresses")
	public ResponseEntity<AddressDto> registerAddress(@PathVariable Long id, @RequestBody @Valid AddressCreateDto addressCreate,
			UriComponentsBuilder uriBuilder) {
		Person person = personRepository.getReferenceById(id);
		Address address = new Address(addressCreate, person);
		addressRepository.save(address);
		URI uri = uriBuilder.path("/person/{id}/addresses").buildAndExpand(address.getId()).toUri();
		return ResponseEntity.created(uri).body(new AddressDto(address));
	}
}