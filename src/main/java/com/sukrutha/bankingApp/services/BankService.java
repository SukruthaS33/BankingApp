package com.sukrutha.bankingApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sukrutha.bankingApp.Repositories.BankRepository;
import com.sukrutha.bankingApp.entities.Bank;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BankService {
	
	@Autowired BankRepository bankRepository;
	
	public Bank getBankDetails(){
		log.info("BankService::getBankDetails");
		Bank bank = null;
		try {
			bank = bankRepository.findById("678658").orElseThrow(()->new Exception("error in getting bank details"));
		}
		catch(Exception e) {
			log.error(e.getMessage());
		}
		
		return bank;
		
	}
 
}
