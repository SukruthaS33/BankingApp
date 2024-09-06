package com.sukrutha.bankingApp.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sukrutha.bankingApp.entities.Customer;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, String>{

	
	public Optional<Customer> findByCustomerEmail(String emailAddress);
	
	
	
}
