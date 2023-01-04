package com.diegochueri.people.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegochueri.people.models.People;

@RestController
public class PeopleController {
	
	@GetMapping("/people")
	public List<People> listAll() {
		People people = new People(null, null);
		return Arrays.asList(people, people, people);
	}
}
