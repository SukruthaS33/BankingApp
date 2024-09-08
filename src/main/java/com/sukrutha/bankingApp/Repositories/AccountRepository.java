package com.sukrutha.bankingApp.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sukrutha.bankingApp.entities.Account;
import com.sukrutha.bankingApp.entities.Customer;

public interface AccountRepository extends JpaRepository<Account, String>{
	
	public List<Account> findByCustomer(Customer customer);

}
