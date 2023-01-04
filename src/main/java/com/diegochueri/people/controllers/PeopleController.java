package com.diegochueri.people.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.diegochueri.people.controllers.dto.PeopleDto;
import com.diegochueri.people.controllers.dto.PersonCreateDto;
import com.diegochueri.people.models.People;
import com.diegochueri.people.repositories.AdressRepository;
import com.diegochueri.people.repositories.PeopleRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/people")
public class PeopleController {
	
	@Autowired
	private PeopleRepository peopleRepository;
	
	@Autowired
	private AdressRepository adressRepository;
	
	@GetMapping
	public List<PeopleDto> listAll() {
		List<People> person = peopleRepository.findAll();
		return PeopleDto.generateDtoList(person);
	}
	
	@PostMapping
	public ResponseEntity<PeopleDto> register(@RequestBody @Valid PersonCreateDto personCreate, UriComponentsBuilder uriBuilder) {
		People person = new People(personCreate);
		peopleRepository.save(person);
		URI uri = uriBuilder.path("/people/{id}").buildAndExpand(person.getId()).toUri();
		return ResponseEntity.created(uri).body(new PeopleDto(person));
	}
	
//	@GetMapping("/[id]")
//	public Optional<PeopleDto> listOne(Long id) {
//		Optional<People> person = peopleRepository.findById(id);
//		if (person.isPresent()) {
//			List<Adress> adresses = adressRepository.findAllByPeopleId(id);
//			if (adresses.isEmpty() == false)
//			person.setAdress();
//		}
//	}
}
