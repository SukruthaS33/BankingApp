package com.sukrutha.bankingApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.sukrutha.bankingApp.entities.Account;
import com.sukrutha.bankingApp.entities.Branch;
import com.sukrutha.bankingApp.entities.Customer;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController{

//	@PostMapping("/{customerId}")
//	public ResponseEntity<String> createAccount(@PathVariable String customerId, @RequestParam String accountType, @RequestParam String branchId) {
//	try {
//		Customer customer = customerService.getCustomerById(customerId);
//		Branch branch = branchService.getBranchById(branchId);
//			String accountNumber = accountService.createAccount(accountType,branchId,customer);
//			return ResponseEntity.status(HttpStatus.OK).body(accountNumber);
//	}
//catch(Exception e) {
//	e.printStackTrace();
//	}
//	
//	
//	@GetMapping("/customer/{customerId}")
//	public ResponseEntity<List<Account>> getAllAccounts(@PathVariable String customerId){
//		
//		
//		try {
//			List<Account> accounts= accountService.getAllAccountsByCustomerId(customerId);
//			return ResponseEntity.status(HttpStatus.OK).body(accounts);
//		}
//		
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@GetMapping("/{accountNumber}")
//	public ResponseEntity<Account> getAccount(@PathVariable String accountNumber){
//		
//		try {
//			Account account = accountService.getAccountByAccountNumber(accountNumber);
//			return ResponseEntity.status(HttpStatus.OK).body(account);
//		}
//		
//		catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//		
//	
//	@PostMapping("/delete/{accountNumber}")
//	public ResponseEntity<Boolean> deleteAccount(@PathVariable String accountNumber){
//	
//		boolean	deleted=false
//		
//		try {
//			deleted = accountService.deleteAccount(accountNumber);
//			if(deleted) {
//				return ResponseEntity.status(HttpStatus.OK).body(deleted);
//			}
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
//	@PostMapping("/{accountNumber}/deposit")
////	public boolean deposit(@PathVariable accountNumber, @RequestParam double amount )
//	
//	
//	
//	}
}



