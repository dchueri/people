package com.diegochueri.people.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diegochueri.people.models.People;

@Controller
public class PeopleController {
	
	@GetMapping("/people")
	@ResponseBody
	public List<People> listAll() {
		People people = new People();
		return Arrays.asList(people, people, people);
	}
}
