package com.sukrutha.bankingApp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sukrutha.bankingApp.entities.CustomerRequest;
import com.sukrutha.bankingApp.services.CustomerRequestService;
import com.sukrutha.bankingApp.services.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/v1/CustomerRequest")
@Slf4j
public class CustomerRequestController {

	@Autowired
	CustomerRequestService customerRequestService;

	@GetMapping("/{requestId}")
	public ResponseEntity<CustomerRequest> getRequestedItemById(@RequestParam String requestId) {
		log.info("CustomerRequestController::getRequestedItemById");
		try {
			CustomerRequest request = customerRequestService.getRequestedItemById(requestId);
			return ResponseEntity.status(HttpStatus.OK).body(request);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("error in getting customer request");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

	}

	@GetMapping("/allRequests")
	public ResponseEntity<List<CustomerRequest>> getAllRequestsofAllCustomers() {
		log.info("CustomerRequestController::getAllRequestsofAllCustomers");
		List<CustomerRequest> allRequests = new ArrayList<CustomerRequest>();
		try {
			allRequests = customerRequestService.getAllRequestsofAllCustomers();
			return ResponseEntity.status(HttpStatus.OK).body(allRequests);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error in getting all new requests");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@PostMapping("/newRequest")
	public ResponseEntity<CustomerRequest> createNewRequest(@RequestBody CustomerRequest request) {
		log.info("CustomerRequestController::createNewRequest");
		CustomerRequest customerNewRequestForCustomer = null;
		try {
			customerNewRequestForCustomer = customerRequestService.createNewRequestForCustomer(request);
			return ResponseEntity.status(HttpStatus.OK).body(customerNewRequestForCustomer);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("error in creating new request");
		}
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customerNewRequestForCustomer);

	}

	@DeleteMapping("/revokeRequest")
	public ResponseEntity<List<CustomerRequest>> deleteRequest(@RequestParam String requestId) {
		log.info("CustomerRequestController::deleteRequest");
		try {

		} catch (Exception e) {

		}
		return null;

	}

}
