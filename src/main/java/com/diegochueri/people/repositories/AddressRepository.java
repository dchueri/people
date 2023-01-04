package com.diegochueri.people.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegochueri.people.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
	List<Address> findAllByPersonId(Long Id);
}
