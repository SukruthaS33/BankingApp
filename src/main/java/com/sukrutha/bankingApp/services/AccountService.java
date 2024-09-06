package com.sukrutha.bankingApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sukrutha.bankingApp.Repositories.AccountRepository;
import com.sukrutha.bankingApp.entities.Account;
import com.sukrutha.bankingApp.entities.Branch;
import com.sukrutha.bankingApp.entities.Customer;
import com.sukrutha.bankingApp.entities.EnumContainer;
import com.sukrutha.bankingApp.entities.EnumContainer.AccountType;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	BranchService branchService;

	public String createAccount(String accountTypeStr, Branch branch, Customer customer) {
		try {
			
			EnumContainer.AccountType accountType = EnumContainer.AccountType.valueOf(accountTypeStr.toUpperCase());
			
			Account account = new Account();
			account.setAccountType(accountType);
			//account.setBalance(0);
			//account.setActive(true);
			account.setBranch(branch);
			account.setCustomer(customer);

			account = accountRepository.save(account);
			return account.getAccountNumber();

		} catch (IllegalArgumentException e) {

			e.printStackTrace();
			log.error("exception while creating account");
		}
		
		catch (Exception e) {

			e.printStackTrace();
			log.error("exception while creating account");
		}
		return "";
	}

//	
//	public boolean deleteAccount(String accountNumber) {//do we need customer id here?
//		boolean deleteStatus=false;
//		Optional<Account> account;
//	try {
//		if(accountNumber!=null) {
//			account=accountRepository.findById(accountNumber);
//			if(account!=null) {
//				accountRepository.deleteById(accountNumber);
//				deleteStatus=true;
//				
//			}
//			
//			else {
//				throw new Exception();
//			}
//			
//			
//		}
//		return deleteStatus;
//	
//	}
//	catch(Exception e) {
//			e.printStackTrace();
//			log.error("Error in deleteAccount (). Unable to delete account ");
//			return deleteStatus;
//			
//	}
//		
//		
//	}
	// think only about the back end now to return /throw exceptions do not worry
	// about the front end at all
	public Account getAccountByAccountNumber(String accountNumber) {
		Account account;
		try {

			account = accountRepository.getById(accountNumber);

			return account;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

//	public List<Account> getAccountsByCustomerId(Iterable<String> customerId){
//		List<Account> accounts;
//
//		try {
//			if(customerId!=null) {
//				accounts = accountRepository.findAllById(customerId);
//				return accounts;
//			}
//			
//			else if(customerId ==null){
//				return null;
//			}
//			
//			
//		}
//		
//		catch(Exception e) {
//			e.printStackTrace();
//			
//			
//		}
//		
//	}
//	
//	public boolean deposit(String accountNumber,double amount) {
//	    boolean depositStatus=false;
//	    
//	    try {
//	    	
//	    }
//	    catch(Exception e) {
//	    	
//	    }
//	
//		
//	}
//	
//	
//	public boolean withdraw(String accountNumber,double amount) {
//		return false;
//		
//		
//	}
}
