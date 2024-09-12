package com.sukrutha.bankingApp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sukrutha.bankingApp.entities.Customer;
import com.sukrutha.bankingApp.entities.Transaction;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminService {

	@Autowired
	CustomerService customerService;

	@Autowired
	TransactionService transactionService;

	public List<Customer> getAllCustomersForAdmin() {
		log.info("AdminService::getAllCustomersForAdmin::");

		List<Customer> allCustomers = new ArrayList<Customer>();

		try {
			allCustomers = customerService.getAllCustomers();
		} catch (Exception e) {
			log.error("error in getAllCustomersForAdmin");
		}

		return allCustomers;
	}

	public List<Transaction> getAllTransactionsForAdmin() {
		log.info("AdminService::getAllTransactionsForAdmin");
		List<Transaction> allTransactions;
		try {
			allTransactions = transactionService.getAllTransactions();

		} catch (Exception e) {
			log.error("AdminService::error in getAllTransactionsForAdmin");
			e.printStackTrace();
		}

		return null;
	}

	
	public boolean retriggerPendingTransactionByAdmin(Transaction transaction) {
		log.info("AdminService::retriggerPendingTransactionByAdmin");
		boolean transactionStatus;
		try {
			transactionStatus = transactionService.retriggerPendingTransactionByAdmin(transaction);

		} catch (Exception e) {
			log.error("AdminService::error in getAllTransactionsForAdmin");
			e.printStackTrace();
		}

		return transactionStatus;
	}
}
