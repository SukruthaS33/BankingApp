package com.sukrutha.bankingApp.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sukrutha.bankingApp.Repositories.TransactionRepository;
import com.sukrutha.bankingApp.entities.Account;
import com.sukrutha.bankingApp.entities.Beneficiary;
import com.sukrutha.bankingApp.entities.EnumContainer.TransactStatus;
import com.sukrutha.bankingApp.entities.EnumContainer.TransferType;
import com.sukrutha.bankingApp.entities.Transaction;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	AccountService accountService;

	@Autowired
	BeneficiaryService beneficiaryService;

	public List<Transaction> getAllTransactions() {
		log.info("TransactionService:getAllTransactions");
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			transactions = transactionRepository.findAll();
		} catch (Exception e) {
			log.error("error in getting all transactions");
			e.printStackTrace();
		}
		return transactions;
	}

	public List<Transaction> getAccountTransactionHistory(String accountNumber) {
		// we need to get both debit and credit transactions happened to an account;
		log.info("TransactionService:getAccountTransactionHistory");
		List<Transaction> AlltransactionsForAccount = new ArrayList<Transaction>();
		try {

			if (accountNumber != null) {
				Account inputAccount = accountService.getAccountByAccountNumber(accountNumber);
				Account beneficiaryAccount = accountService.getAccountByAccountNumber(accountNumber);
				if (inputAccount == null) {
					return AlltransactionsForAccount;
				}
				// we are doing for same account
				log.info(beneficiaryAccount.getAccountNumber()+" "+inputAccount.getAccountNumber()+"bunch of account numbers");
				List<Transaction> transactionsUsingCustomerAccount = transactionRepository
						.findByCustomerAccountAndTransactionStatus(inputAccount, TransactStatus.SUCCESS);

				List<Transaction> transactionsUsingBeneficiaryAccount = transactionRepository
						.findByBeneficiaryAccountAndTransactionStatus(beneficiaryAccount, TransactStatus.SUCCESS);

//we are checking if for a customer in customer_account column if they are ending /recieving the money
				if (transactionsUsingCustomerAccount.size() > 0) {
					for (Transaction transaction : transactionsUsingCustomerAccount) {
						if (transaction.getTransferType().equals(TransferType.SEND_MONEY)) {
							transaction.setDebitOrCredit(true);// True means debit
						} else {
							transaction.setDebitOrCredit(false);
						}
					}
				}
				log.info("transactionsUsingCustomerAccount");
				for (Transaction transaction : transactionsUsingCustomerAccount) {
					log.info(transaction.toString());
				}
//we are checking if for a customer in beneficiary_account column if they are ending /recieving the money				
				if (transactionsUsingBeneficiaryAccount.size() > 0) {
					for (Transaction transaction : transactionsUsingBeneficiaryAccount) {
						if (transaction.getTransferType().equals(TransferType.SEND_MONEY)) {
							transaction.setDebitOrCredit(false);// false means credit
						} else {
							transaction.setDebitOrCredit(true);
						}
					}
				}
				log.info("transactionsUsingBeneficiaryAccount");
				for (Transaction transaction : transactionsUsingBeneficiaryAccount) {
					log.info(transaction.toString());
				}
				boolean addedCustomerTransactions = AlltransactionsForAccount.addAll(transactionsUsingCustomerAccount);
				if (transactionsUsingBeneficiaryAccount.size() > 0) {
					boolean addedBeneficiaryTransactions = AlltransactionsForAccount
							.addAll(transactionsUsingBeneficiaryAccount);
					log.info(" addedBeneficiaryTransactions " + addedBeneficiaryTransactions);
				}

				log.info(" addedCustomerTransactions " + addedCustomerTransactions);
//				if (!AlltransactionsForAccount.addAll(transactionsUsingCustomerAccount)
//						|| !AlltransactionsForAccount.addAll(transactionsUsingBeneficiaryAccount)) {
//					log.error("addition to all transaction list failed!!!!!!!!!!!!!");
//					return new ArrayList<Transaction>();
//				}

			}

		}

		catch (Exception e) {
			log.info("error  finding transaction history");
		}
		return AlltransactionsForAccount;
	}

	public Transaction sendMoney(Transaction transaction, String accountNumber, String beneficiaryAccountNumber,
			double amount) {
		log.info("TransactionService:sendMoney");
		// we have to set other details like id etc in the return transaction type it
		// went missing in this ??
		// TODO add transaction accountno, beneficiaryaccountno etc
		try {
			Account customerAccount = accountService.getAccountByAccountNumber(accountNumber);
			if (customerAccount != null && customerAccount.isActive()) {
				log.info("customer accout is active");
				Beneficiary beneficiary = beneficiaryService
						.getBeneficiaryByBeneficiaryAccountNumber(beneficiaryAccountNumber);// creating a beneficiary
				Account beneficiaryAccount = accountService.getAccountByAccountNumber(beneficiaryAccountNumber);
				// object
				if (beneficiary.isActive()) {// checking if beneficiary is allowed to recieve money (it can be toggled
												// byadmin)
					log.info("beneficiary is Active");
					transaction.setCustomerAccount(customerAccount);
					transaction.setBeneficiaryAccount(beneficiaryAccount);
					log.info(beneficiaryAccountNumber);
					// checking if beneficiary is in the same bank
					Account beneficiaryAccountInSameBank = accountService
							.getAccountByAccountNumber(beneficiaryAccountNumber);
					if (beneficiaryAccountInSameBank != null) {// this means beneficiary is in the same bank
						log.info("this means beneficiary is in the same bank");
						// checking now if beneficiary is linked to the account
						if (accountService.isBeneficiaryExistsInAccount(customerAccount, beneficiary)) {
							log.info("beneficiary exists for the given account");
							// checking if account has enough balance
							if (customerAccount.getBalance() >= amount) {
								log.info("customer has enough balance");
								transaction.setAmount(amount);
								// debiting money from customer account
								accountService.debit(accountNumber, amount);
								// crediting money to beneficiary account because it is in the same bank
								accountService.credit(beneficiaryAccountNumber, amount);
								transaction.setTransactionStatus(TransactStatus.SUCCESS);

							} else {
								transaction.setTransactionStatus(TransactStatus.FAILED);// TODO REASON FOR FAILURE IF
																						// YOU
																						// WANT
							}
						} else {
							transaction.setTransactionStatus(TransactStatus.FAILED);// TODO REASON FOR FAILURE IF YOU
																					// WANT
						}

					} else {
						// Beneficiary is in some other bank
						if (customerAccount.getBalance() >= amount) {
							log.info("beneficiary in a different bank");
							// debiting money from customer account
							accountService.debit(accountNumber, amount);
							// crediting money to beneficiary account by hitting another bank's API call
							Thread.sleep(5000);// mimicing API call
							// in real case scenario lookout for rollback request

							transaction.setTransactionStatus(TransactStatus.SUCCESS);

						} else {
							transaction.setTransactionStatus(TransactStatus.FAILED);// TODO REASON FOR FAILURE IF YOU
																					// WANT
						}
					}
				}
			} else {
				transaction.setTransactionStatus(TransactStatus.FAILED);// TODO REASON FOR FAILURE IF YOU WANT
			}
		} catch (Exception e) {
			log.error("error in sending Money");
			e.printStackTrace();

		}
		log.info("saving transaction");
	
		try {
			
			transactionRepository.save(transaction);
             log.info(transaction.getTransactionId());
			log.info("after save");
		}
		
		catch(Exception e) {
			log.error("error in saving :::::::::");
		}
		
		return transaction;
	}

	public Transaction receiveMoney(Transaction transaction, String accountNumber, String beneficiaryAccountNumber,
			double amount) {
		log.info("TransactionService:receiveMoney");

		try {
			// Fetch the account of the person requesting money
			Account customerAccount = accountService.getAccountByAccountNumber(accountNumber);
			if (customerAccount != null && customerAccount.isActive()) {
				Beneficiary beneficiary = beneficiaryService
						.getBeneficiaryByBeneficiaryAccountNumber(beneficiaryAccountNumber);// creating a beneficiary
				Account beneficiaryAccount = accountService.getAccountByAccountNumber(beneficiaryAccountNumber);																	// object
				if (beneficiary.isActive()) {// checking if beneficiary is allowed to recieve money (it can be toggled
												// byadmin)

					transaction.setCustomerAccount(customerAccount);
					transaction.setBeneficiaryAccount(beneficiaryAccount);

					// checking if beneficiary is in the same bank
					Account beneficiaryAccountInSameBank = accountService
							.getAccountByAccountNumber(beneficiaryAccountNumber);
					if (beneficiaryAccountInSameBank != null) {// this means beneficiary is in the same bank
						// checking if account has enough balance
						if (beneficiaryAccountInSameBank.getBalance() >= amount) {
							transaction.setAmount(amount);
							// debiting money from customer account
							accountService.debit(beneficiaryAccountNumber, amount);
							// crediting money to account because it is in the same bank
							accountService.credit(accountNumber, amount);
							transaction.setTransactionStatus(TransactStatus.SUCCESS);

						} else {
							transaction.setTransactionStatus(TransactStatus.FAILED);// TODO REASON FOR FAILURE IF YOU
																					// WANT
						}

					} else {
						log.info("beneficiary not in same bank");

						// debiting money from beneficiary account by hitting another bank's API call
						Thread.sleep(5000);// mimicing API call
						// in real case scenario lookout for rollback request
						accountService.credit(accountNumber, amount);// credit to customer in our bank
						transaction.setTransactionStatus(TransactStatus.SUCCESS);

					}

				}
			}
		} catch (Exception e) {
			log.error("error in crediting money to the beneficiary account");
			e.printStackTrace();
		}
		transactionRepository.save(transaction);
		return transaction;
	}

	public Transaction depositCash(Transaction transaction, String accountNumber, double amount) {
		log.info("TransactionService:depositCash");

		try {

			// check if accountNumber is valid
			Account customerAccount = accountService.getAccountByAccountNumber(accountNumber);
			if (customerAccount != null) {
				if (customerAccount.isActive()) {
					transaction.setCustomerAccount(customerAccount);
					transaction.setAmount(amount);
					if (accountService.credit(accountNumber, amount)) {
						transaction.setTransactionStatus(TransactStatus.SUCCESS);
					}

					else {
						transaction.setTransactionStatus(TransactStatus.FAILED);
					}
				} else {
					transaction.setTransactionStatus(TransactStatus.FAILED);
				}

			}

		} catch (Exception e) {
			transaction.setTransactionStatus(TransactStatus.FAILED);
			log.error("error in getting customer account");
			e.printStackTrace();
		}
		return transaction;
	}

	public Transaction withdrawCash(Transaction transaction, String accountNumber, double amount) {
		log.info("TransactionService:withdrawCash");

		try {

			// check if accountNumber is valid
			Account customerAccount = accountService.getAccountByAccountNumber(accountNumber);
			if (customerAccount != null) {
				if (customerAccount.isActive()) {
					transaction.setCustomerAccount(customerAccount);
					transaction.setAmount(amount);

					if (accountService.debit(accountNumber, amount)) {
						transaction.setTransactionStatus(TransactStatus.SUCCESS);
					}

					else {
						transaction.setTransactionStatus(TransactStatus.FAILED);
					}
				} else {
					transaction.setTransactionStatus(TransactStatus.FAILED);
				}

			}

		} catch (Exception e) {
			transaction.setTransactionStatus(TransactStatus.FAILED);
			log.error("error in getting customer account");
			e.printStackTrace();
		}
		return transaction;
	}

}
