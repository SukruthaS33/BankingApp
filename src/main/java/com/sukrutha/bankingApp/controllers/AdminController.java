package com.sukrutha.bankingApp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sukrutha.bankingApp.entities.Customer;
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
		log.info("CustomerController::getCustomerDetailsByCustomerId");
		Customer customer = null;

		try {
			customer = adminService.getCustomerDetailsByCustomerId(customerId);

			if (customer != null) {

				return ResponseEntity.status(HttpStatus.OK).body(customer);
			}

			else if (customer == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}

		}

		catch (Exception e) {
			log.error(customerId);
			log.error("encountered issue in getCustomerDetails() caller method");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
		return null;
	}
}
