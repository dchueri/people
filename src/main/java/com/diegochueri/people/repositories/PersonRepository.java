package com.diegochueri.people.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegochueri.people.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
