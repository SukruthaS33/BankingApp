package com.sukrutha.bankingApp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sukrutha.bankingApp.entities.Customer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminService {

	@Autowired
	CustomerService customerService;

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

}
