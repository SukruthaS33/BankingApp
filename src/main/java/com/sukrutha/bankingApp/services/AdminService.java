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
	
	@Autowired
	CustomerRequestService customerRequestService;

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
	List<CustomerRequest> allSubmittedRequests = new ArrayList<>();
	try {
		allSubmittedRequests=customerRequestService.getAllRequestsofAllCustomers();
		return allSubmittedRequests;
	}
	
	catch(Exception e) {
		log.error("error in getting customer requests");
	}
	return allSubmittedRequests;
}



public boolean approveOrRejectSubmittedRequest(String requestId,String requestStatus, String adminComment) {
	log.info("AdminService approveOrRejectSubmittedRequest");
	boolean approveOrRejectStatus = false;
	try {
	  approveOrRejectStatus = customerRequestService.approveOrRejectCustomerRequest(requestId, requestStatus,adminComment);
	return approveOrRejectStatus;
	}
	catch(Exception e) {
		e.printStackTrace();
		log.error("error in approving/reject request");
	}
	return approveOrRejectStatus;
}

}
