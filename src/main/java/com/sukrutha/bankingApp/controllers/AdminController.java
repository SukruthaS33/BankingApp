package com.sukrutha.bankingApp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<List<Customer>> getAllCustomersForAdmin()
	{
		log.info("getAllCustomersForAdmi::started");
		List<Customer> allCustomers= new ArrayList<Customer>();
		try {
			allCustomers= adminService.getAllCustomersForAdmin();
		}
		catch(Exception e) {
			log.error("getAllCustomersForAdmin ::error in getting all customers ");
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(allCustomers);
	}
}
