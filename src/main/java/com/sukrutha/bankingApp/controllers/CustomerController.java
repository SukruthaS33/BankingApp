package com.sukrutha.bankingApp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sukrutha.bankingApp.entities.Customer;
import com.sukrutha.bankingApp.services.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Customer customer) {
		String registeredUserId = "";
		log.info("CustomerController::register");
		try {
			registeredUserId = customerService.register(customer);
			if (registeredUserId != null) {
				return ResponseEntity.status(HttpStatus.OK).body(registeredUserId);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("issue registering user");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("issue registering you");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam String customerEmail, @RequestParam String customerPassword) {
		log.info("CustomerController::login");
		try {
			if (customerService.login(customerEmail, customerPassword)) {
				return ResponseEntity.status(HttpStatus.OK).body("login success");
			} else {
				// If loginOutput is null, return an error response
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("login failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Return an error response in case of exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login.");
		}
	}

	@GetMapping("/test")
	public ResponseEntity<String> test() {

		return ResponseEntity.status(HttpStatus.OK).body("working");

	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerDetailsByCustomerId(@PathVariable String customerId) {
		log.info("CustomerController::getCustomerDetailsByCustomerId");
		Customer customer = null;

		try {
			customer = customerService.getCustomerDetailsByCustomerId(customerId);

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
