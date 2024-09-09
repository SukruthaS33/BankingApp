package com.sukrutha.bankingApp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sukrutha.bankingApp.entities.Account;
import com.sukrutha.bankingApp.entities.Customer;
import com.sukrutha.bankingApp.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

	public List<Transaction> findByCustomerAccount(Account account);

}
