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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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
import com.diegochueri.people.services.AddressService;
import com.diegochueri.people.services.PersonService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	@Autowired
	private AddressService addressService;
	
	@GetMapping
	public ResponseEntity<List<PersonDto>> listAllPersons() {
		PersonDto personDto = new PersonDto();
		List<Person> personList = personService.getAll();
		List<PersonDto> personDtoList = personDto.generateDtoList(personList);
		return ResponseEntity.ok().body(personDtoList);
	}

	@PostMapping
	public ResponseEntity<PersonDto> registerPerson(@RequestBody @Valid PersonCreateDto personCreate) {
		Person person = personService.add(personCreate);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/person/{id}").buildAndExpand(person.getId()).toUri();
		return ResponseEntity.created(uri).body(new PersonDto(person));
	}

	@GetMapping("/{id}")
	public PersonDetailsDto listOnePerson(@PathVariable Long id) {
		Person person = personService.getOneById(id);
		return new PersonDetailsDto(person);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PersonDto> updatePerson(@PathVariable Long id, @RequestBody PersonUpdateDto personUpdate) {
		Person person = personService.getOneById(id);
		Person personUpdated = personService.update(person, personUpdate);
		return ResponseEntity.ok(new PersonDto(personUpdated));
	}

	@PostMapping("/{id}/addresses")
	public ResponseEntity<AddressDto> registerAddress(@PathVariable Long id,
			@RequestBody @Valid AddressCreateDto addressCreate) {
		Person person = personService.getOneById(id);
		Address address = addressService.add(addressCreate, person);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/person/{id}").buildAndExpand(address.getId()).toUri();
		return ResponseEntity.created(uri).body(new AddressDto(address));
	}

	@PutMapping("/{id}/addresses/{addressId}")
	@Transactional
	public ResponseEntity<AddressDto> updateAddress(@PathVariable Long id, @PathVariable Long addressId,
			@RequestBody AddressUpdateDto addressUpdate) {
		Address address = addressService.getOneById(id);
		Address addressUpdated = addressService.update(address, addressUpdate);
		return ResponseEntity.ok(new AddressDto(addressUpdated));
	}
}
