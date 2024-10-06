package com.sukrutha.bankingApp.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sukrutha.bankingApp.entities.Customer;
import com.sukrutha.bankingApp.entities.CustomerRequest;
import com.sukrutha.bankingApp.services.AdminService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/admin")
public class AdminController {
	@Autowired
	AdminService adminService;

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomersForAdmin() {
		log.info("AdminController::getAllCustomersForAdmin");
		List<Customer> allCustomers = new ArrayList<Customer>();
		try {
			allCustomers = adminService.getAllCustomersForAdmin();
		} catch (Exception e) {
			log.error("getAllCustomersForAdmin ::error in getting all customers ");
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(allCustomers);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerDetailsByCustomerId(@PathVariable String customerId) {
		log.info("AdminController::getCustomerDetailsByCustomerId");
		Customer customer = null;
		try {
			customer = adminService.getCustomerDetailsByCustomerId(customerId);
			if (customer != null) {
				return ResponseEntity.status(HttpStatus.OK).body(customer);
			} else if (customer == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		} catch (Exception e) {
			log.error(customerId);
			log.error("encountered issue in getCustomerDetails() caller method");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return null;
	}

	@GetMapping("/customerNewAccountRequests")
	public ResponseEntity<List<CustomerRequest>> getAllCustomerAccountRequests() {
		log.info("AdminController::getAllCustomerAccountRequests");
		List<CustomerRequest> customerRequests = new ArrayList<>();
		try {
			customerRequests = adminService.getAllCustomerAccountRequests();
			if (customerRequests != null) {
				return ResponseEntity.status(HttpStatus.OK).body(customerRequests);
			} else if (customerRequests == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerRequests);
			}
		} catch (Exception e) {

			log.error("encountered issue in getCustomerDetails() caller method");

		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customerRequests);
	}

	@PostMapping("/{requestId}")
	public ResponseEntity<Boolean> approveOrRejectSubmittedRequest(@PathVariable String requestId,
			@RequestParam String requestStatus ,@RequestParam(required=false) String adminComment) {
		log.info("AdminController::approveSubmittedRequest");
		boolean approveOrRejectStatus = false;
		try {
			approveOrRejectStatus = adminService.approveOrRejectSubmittedRequest(requestId, requestStatus,adminComment);
			return ResponseEntity.status(HttpStatus.OK).body(approveOrRejectStatus);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error in updating request status");

		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(approveOrRejectStatus);
	}

}