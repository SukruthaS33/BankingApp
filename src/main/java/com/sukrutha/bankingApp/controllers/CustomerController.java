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
import com.sukrutha.bankingApp.services.RoleService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	RoleService roleService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody @Valid Customer customer) {
		String registeredUserId = "";
		log.info("CustomerController::register");
		try {
			// get role here

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

	// front-end required change
	// after login we need to get customer id in order to load corresponding details
	// so we must not return boolean for a success login(usually)
	@PostMapping("/login")
	public ResponseEntity<Customer> login(@RequestParam String customerEmail, @RequestParam String customerPassword) {
		log.info("CustomerController::login");
		Customer customer = null;
		try {
			customer = customerService.login(customerEmail, customerPassword);
			if (customer != null) {
				return ResponseEntity.status(HttpStatus.OK).body(customer);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// Return an error response in case of exception

		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customer);
	}

	@GetMapping("/test")
	public ResponseEntity<String> test() {

		return ResponseEntity.status(HttpStatus.OK).body("working");

	}

//test this also
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

	@PostMapping("/{customerId}/update/{nameOfUpdate}")
	public ResponseEntity<Boolean> updateCustomerDetails(
	        @PathVariable String customerId,
	        @PathVariable String nameOfUpdate,
	        @RequestBody Customer customer) {

		boolean updateReqSubmitStatus = false;

		try {
			log.info(customerId);
			log.info(nameOfUpdate);
			log.info(customer.getCustomerAddress().getHouseNumber());
			log.info(customer.getCustomerAddress().getLocality());
			updateReqSubmitStatus = customerService.updateCustomerDetails(customerId, customer, nameOfUpdate);

			if (updateReqSubmitStatus) {
				return ResponseEntity.status(HttpStatus.OK).body(updateReqSubmitStatus);
			}

			else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(updateReqSubmitStatus);
			}

		}

		catch (Exception e) {
			log.error(customerId);
			log.error("encountered issue in getCustomerDetails() caller method");
			

		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(updateReqSubmitStatus);

	}

}
