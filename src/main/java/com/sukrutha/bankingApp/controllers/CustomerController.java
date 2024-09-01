package com.sukrutha.bankingApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sukrutha.bankingApp.entities.Customer;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Customer customer){
		String registeredUserId="";
		
		try {
			//registeredUserId= customerSerice.register(customer);
			
		}
		
		catch(Exception e){
			e.printStackTrace();			
		}
		
		if(registeredUserId.equalsIgnoreCase("")){
				return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(registeredUserId);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(registeredUserId); 
	}
	
	@PostMapping("/login")
	public ResponseEntity<Boolean> login(@RequestParam String customerId, @RequestParam String password){
		 boolean isLoggedIn=false;
		 
		 try {
			 //isLoggedIn = customerService.login(customerId,password)
		 }
		 
		 
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 if(!isLoggedIn) {
			 return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(isLoggedIn);
		 }
		 
		 return ResponseEntity.status(HttpStatus.OK).body(isLoggedIn);
	}
	
	
	
	  @GetMapping("/test")
	  public ResponseEntity<String> test(){
	  
		  
		  return ResponseEntity.status(HttpStatus.OK).body("working");
	  
	  
	  }
	 
	
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerDetails(@PathVariable String customerId){
		
		try {
			//Customer customer = customerService.getCustomerById(customerId);
		
			return ResponseEntity.status(HttpStatus.OK).body(null);
			
		}
		
		catch(Exception e){
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	

}
