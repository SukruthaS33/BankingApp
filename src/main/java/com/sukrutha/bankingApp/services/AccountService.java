package com.sukrutha.bankingApp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sukrutha.bankingApp.Repositories.AccountRepository;
import com.sukrutha.bankingApp.entities.Account;
import com.sukrutha.bankingApp.entities.Beneficiary;
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

	@Autowired
	BeneficiaryService beneficiaryService;

	@Autowired
	CustomerService customerService;

	public String createAccount(String accountTypeStr, Branch branch, Customer customer) {
		try {

			EnumContainer.AccountType accountType = EnumContainer.AccountType.valueOf(accountTypeStr.toUpperCase());

			Account account = new Account();
			// account.setAccountType(accountType);
			// account.setBalance(0);
			account.setActive(true);
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

	public String updateAccount(Account account) {
		try {

			// updating existing account

			account = accountRepository.save(account);
			return account.getAccountNumber();

		} catch (IllegalArgumentException e) {

			e.printStackTrace();
			log.error("exception while updating account");
		}

		catch (Exception e) {

			e.printStackTrace();
			log.error("exception while updating account");
		}
		return account.getAccountNumber();
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
		Account account = null;
		try {

			account = accountRepository.findById(accountNumber)
					.orElseThrow(() -> new Exception("account does not exist"));

		} catch (Exception e) {
			log.error("error finding the account");
			e.printStackTrace();

		}
		return account;
	}

	public boolean addBeneficiaryToAccount(String accountNumber, Beneficiary beneficiary) {

		try {
			// the job of this method is to connect the beneficiary with the account number
			// NOT to create a beneficiary(this will be done in beneficiary controller

			// Step1

			// getting account from AccountNumber;

			Account account = this.getAccountByAccountNumber(accountNumber);
			if (account == null || !account.isActive()) {// if account is not active user cannot add any beneficiary
				return false;
			}
			// Step2

			// user is able to provide beneficiary account number only not id so..
			// checking if beneficiary exists in beneficiary table
			Beneficiary existingBeneficiary = beneficiaryService
					.getBeneficiaryByBeneficiaryAccountNumber(beneficiary.getBeneficiaryAcctNumber());

			if (existingBeneficiary == null) {
				// if beneficiary does not exist in beneficiary table saving the new beneficiary
				// in the table
				existingBeneficiary = beneficiaryService.addBeneficiary(beneficiary);// after adding the person becomes
																						// existing beneficiary
				if (existingBeneficiary == null) {
					return false;
				}
			}

			// Step 3

			// Linking account to beneficiary
			List<Beneficiary> beneficiaries = account.getBeneficiaries();
			if (beneficiaries == null) {
				beneficiaries = new ArrayList<Beneficiary>();
			}
			beneficiaries.add(existingBeneficiary);
			account.setBeneficiaries(beneficiaries);

			List<Account> beneficiaryAccounts = beneficiary.getAccount();// should have been accounts
			if (beneficiaryAccounts == null) {
				beneficiaryAccounts = new ArrayList<Account>();
			}
			beneficiaryAccounts.add(account);
			beneficiary.setAccount(beneficiaryAccounts);

			// Step 4
			// updating both account and beneficiary

			this.updateAccount(account);
			beneficiaryService.updateBeneficiary(existingBeneficiary);
			return true;
		} catch (Exception e) {
			log.error("error adding beneficiary");
			e.printStackTrace();
		}
		return false;
	}

	public List<Account> getAccountsByCustomerId(String customerId) {
		log.info("getAccountsByCustomerId:: fetching accounts for a given customer");
		List<Account> accounts = new ArrayList<Account>();

		try {
			if (customerId != null) {
				Customer inputCustomer = customerService.getCustomerById(customerId);
				if (inputCustomer == null) {
					return accounts;// accounts will be an empty arraylist
				}
				accounts = accountRepository.findByCustomer(inputCustomer);

			}

			else if (customerId == null) {
				return accounts;// accounts will be an empty arraylist
			}

		}

		catch (Exception e) {
			log.error("error while fetching accounts for a given customer");
			e.printStackTrace();

		}
		return accounts;

	}
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
