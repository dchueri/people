package com.diegochueri.people.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegochueri.people.models.People;

public interface PeopleRepository extends JpaRepository<People, Long> {

}
