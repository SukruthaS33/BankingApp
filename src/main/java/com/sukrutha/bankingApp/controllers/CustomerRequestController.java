package com.sukrutha.bankingApp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sukrutha.bankingApp.entities.CustomerRequest;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/v1/CustomerRequest")
@Slf4j
public class CustomerRequestController {

	@GetMapping("/{requestId}")
	public ResponseEntity<CustomerRequest> getRequestedItemById(@RequestParam String requestId) {
			log.info("CustomerRequestController::getRequestedItemById");
		try {

		} catch (Exception e) {

		}
		return null;

	}

	@GetMapping("/allRequests")
	public ResponseEntity<List<CustomerRequest>> getAllRequests() {
		log.info("CustomerRequestController::getAllRequests");

		try {

		} catch (Exception e) {

		}
		return null;

	}

	@PostMapping("/newRequest")
	public ResponseEntity<List<CustomerRequest>> createNewRequest(@RequestBody CustomerRequest request) {
		log.info("CustomerRequestController::createNewRequest");
		try {

		} catch (Exception e) {

		}
		return null;

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
