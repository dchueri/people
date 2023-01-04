package com.diegochueri.people.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegochueri.people.models.Adress;

public interface AdressRepository extends JpaRepository<Adress, Long> {
	
	List<Adress> findAllByPersonId(Long Id);
}
