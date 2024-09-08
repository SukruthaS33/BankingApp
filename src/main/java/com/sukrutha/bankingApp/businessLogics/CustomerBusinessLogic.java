package com.sukrutha.bankingApp.businessLogics;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sukrutha.bankingApp.Repositories.CustomerRepository;
import com.sukrutha.bankingApp.customExceptions.InputException;
import com.sukrutha.bankingApp.entities.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerBusinessLogic {

		@Autowired
		CustomerRepository customerRepository;
	
		public boolean verifyRegisteringCustomer(Customer customer) {
			log.info("CustomerBusinessLogic::verifyRegisteringCustomer");
			
			try {
				//verifying duplicate email ID
				//Customer existingCustomer=customerRepository.findByCustomerEmail(customer.getCustomerEmail()).orElseThrow(()->new InputException("customer already exists"));
				Optional<Customer> customerOptional  = customerRepository.findByCustomerEmail(customer.getCustomerEmail());
				Customer savedUser = customerOptional.orElseThrow(()->new InputException("customer is not there in the table"));
					
					return false;//Customer existingCustomer = customerOptional.get();
					
			
			}
			catch(InputException e) {
				log.info("message "+e.getMessage());
				return true;
			}
			catch(Exception e) {
				log.error("ERROR verifyRegisteringCustomer");
				e.printStackTrace();
			}
			/// if all the checks are completed return true 
			return false;
		}
		
		
		public String generateRandomNumber() {
			log.info("CustomerBusinessLogic:generateRandomNumber");
			long min = 1000000000L;
			long max = 9999999999L;

			long randomNum = min + (long) (Math.random() * ((max - min) + 1));
			return String.valueOf(randomNum);

		}
}
