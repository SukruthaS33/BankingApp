package com.sukrutha.bankingApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.sukrutha.bankingApp.entities.Account;
import com.sukrutha.bankingApp.entities.Beneficiary;
import com.sukrutha.bankingApp.entities.Branch;
import com.sukrutha.bankingApp.entities.Customer;
import com.sukrutha.bankingApp.services.AccountService;
import com.sukrutha.bankingApp.services.BranchService;
import com.sukrutha.bankingApp.services.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/accounts")
@Slf4j
public class AccountController {

	@Autowired
	AccountService accountService;

	@Autowired
	CustomerService customerService;

	@Autowired
	BranchService branchService;

//	@GetMapping("/customer/{customerId}")
//	public ResponseEntity<List<Account>> getAllAccounts(@PathVariable String customerId){
//		
//		
//		try {
//			List<Account> accounts= accountService.getAccountsByCustomerId(customerId);
//			return ResponseEntity.status(HttpStatus.OK).body(accounts);
//		}
//		
//		catch(Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//			
//		}
//	}
//	

	@GetMapping("/{accountNumber}")
	public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {

		try {
			Account account = accountService.getAccountByAccountNumber(accountNumber);

			return ResponseEntity.status(HttpStatus.OK).body(account);
		}

		catch (Exception e) {
			e.printStackTrace();

		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@PostMapping("/{customerId}")
	public ResponseEntity<String> createAccount(@PathVariable String customerId, @RequestParam String accountType,
			@RequestParam String branchId) {
		log.info("AccountController::createAccount");
		try {
			Customer customer = customerService.getCustomerById(customerId);
			Branch branch = branchService.getBranchById(branchId);
			if (customer != null && branch != null) {
				log.info("customer and branch found");

				String accountNumber = accountService.createAccount(accountType, branch, customer);
				if (accountNumber == "" || accountNumber == null) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
				}
				return ResponseEntity.status(HttpStatus.OK).body(accountNumber);
			}

			else {
				log.error("createAccount::customer/branch not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@PostMapping("/addbeneficiary/{accountNumber}")
	public ResponseEntity<Boolean> addBeneficiaryToAccount(@PathVariable String accountNumber,
			@RequestBody Beneficiary beneficiary) {
		log.info("addBeneficiaryToAccount::adding Beneficiary "+ beneficiary);
		try {
			if (accountService.addBeneficiaryToAccount(accountNumber, beneficiary)) {
				return ResponseEntity.status(HttpStatus.OK).body(true);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	}
//	@PostMapping("/delete/{accountNumber}")
//	public ResponseEntity<Boolean> deleteAccount(@PathVariable String accountNumber){
//	
//		boolean	deleted=false;
//		
//		try {
//			deleted = accountService.deleteAccount(accountNumber);
//			if(deleted) {
//				return ResponseEntity.status(HttpStatus.OK).body(deleted);
//			}
//			
//			
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.OK).body(deleted);
//		}
//			
//		}
//		
//		
//	

//	public boolean deposit(@PathVariable accountNumber, @RequestParam double amount )

	// withdraw method

}
