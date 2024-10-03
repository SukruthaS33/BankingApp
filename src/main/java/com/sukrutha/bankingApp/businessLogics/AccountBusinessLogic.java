package com.sukrutha.bankingApp.businessLogics;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AccountBusinessLogic {

	
	public String generateRandomNumber() {
		log.info("AccountBusinessLogic:generateRandomNumber");
		long min = 1000000000L;
		long max = 9999999999L;

		long randomNum = min + (long) (Math.random() * ((max - min) + 1));
		return String.valueOf(randomNum);

	}
	
	
}
