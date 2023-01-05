package com.diegochueri.people.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.diegochueri.people.models.Person;
import com.diegochueri.people.repositories.PersonRepository;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private PersonRepository repository;

	@Bean
	void startDB() {
		int numberOfPersons = 3;
		int count = 1;
		LocalDate birthDate = LocalDate.parse("1995-03-16");
		while (numberOfPersons > 0) {
			Person person = new Person();
			person.setName("Person " + count);
			person.setBirthDate(birthDate);
			repository.save(person);
			count++;
			numberOfPersons--;
		}
	}
}
