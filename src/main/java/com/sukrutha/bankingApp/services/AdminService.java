package com.sukrutha.bankingApp.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sukrutha.bankingApp.entities.Customer;
import com.sukrutha.bankingApp.entities.CustomerRequest;

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

		
		for(int i=0;i<allCustomers.size();i++) {
			if(allCustomers.get(i).getCustomerName().contains("ADMIN")) {
				allCustomers.remove(i);
			}
		}
	
		return allCustomers;
		
	}



public Customer getCustomerDetailsByCustomerId(String customerId) {
	Customer customer;
	try {
		customer = customerService.getCustomerDetailsByCustomerId(customerId);
				

		if (customer != null) {
			return customer;
		} else {
			throw new Exception();
		}
	}

	catch (Exception e) {
		log.error("internal error in getCustomerDetails()  method");
		e.printStackTrace();
		return null;
	}
}



public List<CustomerRequest> getAllCustomerAccountRequests() {
	
	log.info("AdminService getAllCustomerAccountRequests");
	try {
		
	}
	
	catch(Exception e) {
		log.error("error in getting customer requests");
	}
	return null;
}

}
