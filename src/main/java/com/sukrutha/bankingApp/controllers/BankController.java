package com.sukrutha.bankingApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sukrutha.bankingApp.entities.Account;
import com.sukrutha.bankingApp.entities.Bank;
import com.sukrutha.bankingApp.services.AccountService;
import com.sukrutha.bankingApp.services.BankService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/bank")
@Slf4j
public class BankController {
	
	@Autowired
	BankService bankService;
	
	@GetMapping("/")
	public ResponseEntity<Bank> getBankDetails() {
		log.info("AccountController::getAccountByAccountNumber");
		Bank bank=null;
		try {
			 bank = bankService.getBankDetails();

			return ResponseEntity.status(HttpStatus.OK).body(bank);
		}

		catch (Exception e) {
			e.printStackTrace();

		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bank);
	}


}
