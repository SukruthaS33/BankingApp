package com.sukrutha.bankingApp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sukrutha.bankingApp.Repositories.AccountRepository;
import com.sukrutha.bankingApp.businessLogics.AccountBusinessLogic;
import com.sukrutha.bankingApp.businessLogics.CustomerBusinessLogic;
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

	@Autowired
	AccountBusinessLogic accountBusinessLogic;

	public String createAccount(String accountTypeStr, Branch branch, Customer customer) {
		log.info("AccountService::createAccount");
		try {

			EnumContainer.AccountType accountType = EnumContainer.AccountType.valueOf(accountTypeStr.toUpperCase());

			Account account = new Account();

			String accountNumber = accountBusinessLogic.generateRandomNumber();// rename it
			account.setAccountNumber(accountNumber);
			// account.setAccountType(accountType);
			// account.setBalance(0);
			account.setActive(true);
			account.setBranch(branch);
			account.setCustomer(customer);

			account = accountRepository.save(account);
			return accountNumber;

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
		log.info("AccountService:updateAccount");
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
		log.info("AccountService::getAccountByAccountNumber");
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
		log.info("AccountService::addBeneficiaryToAccount");
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
		log.info("AccountService::getAccountsByCustomerId:: fetching accounts for a given customer");
		List<Account> accounts = new ArrayList<Account>();

		try {
			if (customerId != null) {
				Customer inputCustomer = customerService.getCustomerById(customerId);
				if (inputCustomer == null) {
					return accounts;// accounts will be an empty arraylist
				}
				accounts = accountRepository.findByCustomer(inputCustomer);

			}

		}

		catch (Exception e) {
			log.error("error while fetching accounts for a given customer");
			e.printStackTrace();

		}
		return accounts;

	}

	public boolean credit(String accountNumber, double amount) {

		log.info("AccountService::credit");

		boolean creditStatus = false;

		try {
			Account account = this.getAccountByAccountNumber(accountNumber);
			if (account.isActive()) {

				account.setBalance(account.getBalance() + amount);
				accountRepository.save(account);
				creditStatus = true;

			} else {
				log.error("ACCOUNT NOT ACTIVE");
			}

		} catch (Exception e) {

		}
		return creditStatus;

	}

	public boolean debit(String accountNumber, double amount) {
		log.info("AccountService::debit");

		boolean debitStatus = false;

		try {
			Account account = this.getAccountByAccountNumber(accountNumber);
			if (account.isActive()) {
				if (amount <= account.getBalance()) {
					account.setBalance(account.getBalance() - amount);
					accountRepository.save(account);
					debitStatus = true;
				} else {
					log.error("NOT ENOUGH BALANCE IN ACCOUNT!!");

				}

			} else {
				log.error("ACCOUNT NOT ACTIVE");
			}

		} catch (Exception e) {

		}
		return debitStatus;
	}

	public boolean isBeneficiaryExistsInAccount(Account account, Beneficiary beneficiary) {
		log.info("AcountService::isBeneficiaryExistsInAccount");

		boolean beneficiaryLinked = false;
		try {
			if (accountRepository.existsBeneficiaryInAccount(account.getAccountNumber(),
					beneficiary.getBeneficiaryId()) == 1) {
				beneficiaryLinked = true;
			}
		} catch (Exception e) {
			log.error("error in getting beneficiary linking details");
			e.printStackTrace();
		}
		return beneficiaryLinked;
	}

	public boolean pause(String accountNumber, boolean activeStatus) {
		boolean pauseStatus = false;
		try {

			Account account = this.getAccountByAccountNumber(accountNumber);

			if (account != null) {
				if (account.isActive()) {
					pauseStatus = activeStatus;
					account.setActive(pauseStatus);
					accountRepository.save(account);
				}
			}

		} catch (Exception e) {
			log.error("error in pausing account");
			e.printStackTrace();
		}
		return pauseStatus;
	}

	public boolean enable(String accountNumber, boolean activeStatus) {
		boolean enableStatus = false;
		try {

			Account account = this.getAccountByAccountNumber(accountNumber);

			if (account != null) {
				if (!account.isActive()) {
					enableStatus = activeStatus;
					account.setActive(enableStatus);
					accountRepository.save(account);
				}
			}

		} catch (Exception e) {
			log.error("error in pausing account");
			e.printStackTrace();
		}
		return enableStatus;
	}

	public List<Beneficiary> findAllBeneficiariesInAccount(String accountNumber) {
		
		List<Beneficiary> beneficiariesOfAccount = new ArrayList<Beneficiary>();
		try {
			log.info("account number"+accountNumber);
			beneficiariesOfAccount = accountRepository.findAllBeneficiariesInAccount(accountNumber);

		} catch (Exception e) {
			log.error("error in getting all beneficiaries linked to account");
			e.printStackTrace();
		}
		return beneficiariesOfAccount;
	}

//	public boolean deleteBeneficiariesLinkedToAccount(String accountNumber, ArrayList<Beneficiary> beneficiaries) {
//		log.info("AccountService::deleteBeneficiaryLinkedToAccount");
//		try {
//			// Fetch the account by accountNumber
//			Account customerAccount = accountRepository.findById(accountNumber)
//					.orElseThrow(() -> new Exception("customer account not found"));
//			// there is no need to check if beneficiary is in beneficiary table or in our
//			// bank
//			for (Beneficiary beneficiary : beneficiaries) {
//				if (accountRepository.existsBeneficiaryInAccount(customerAccount,
//						beneficiary.getBeneficiaryId())) {
//					// delete beneficiary using beneficiary service
//					beneficiaryService.deleteBeneficiary(beneficiary);
//				}
//			}
//
//		}
//
//		catch (Exception e) {
//			e.getMessage();
//			log.error("error in deleting a beneficiary");
//
//		}
//		return false;
//	}

}
