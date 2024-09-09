package com.sukrutha.bankingApp.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sukrutha.bankingApp.Repositories.TransactionRepository;
import com.sukrutha.bankingApp.entities.Account;
import com.sukrutha.bankingApp.entities.EnumContainer.TransactStatus;
import com.sukrutha.bankingApp.entities.Transaction;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	AccountService accountService;

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
		log.info("TransactionService:getAccountTransactionHistory");
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			if (accountNumber != null) {
				Account inputAccount = accountService.getAccountByAccountNumber(accountNumber);
				if (inputAccount == null) {
					return transactions;
				}
				transactions = transactionRepository.findByCustomerAccount(inputAccount);
			}

		}

		catch (Exception e) {
			log.info("error  finding transaction history");
		}
		return transactions;
	}

	public Transaction sendMoney(Transaction transaction, String accountNumber, String beneficiaryAccountNumber,
			double amount) {
		log.info("TransactionService:sendMoney");

		try {
			Account customerAccount = accountService.getAccountByAccountNumber(accountNumber);
			if (customerAccount.isActive()) {
				// checking if beneficiary is in the same bank
				Account beneficiaryAccountInSameBank = accountService
						.getAccountByAccountNumber(beneficiaryAccountNumber);
				if (beneficiaryAccountInSameBank != null) {// this means beneficiary is in the same bank
					// checking now if beneficiary is linked to the account
					if (accountService.isBeneficiaryExistsInAccount(accountNumber, beneficiaryAccountNumber)) {
						// checking if account has enough balance
						if (customerAccount.getBalance() >= amount) {
							// debiting money from customer account
							accountService.debit(accountNumber, amount);
							// crediting money to beneficiary account because it is in the same bank
							accountService.credit(beneficiaryAccountNumber, amount);
							transaction.setTransactionStatus(TransactStatus.SUCCESS);

						} else {
							transaction.setTransactionStatus(TransactStatus.FAILED);// TODO REASON FOR FAILURE IF YOU
																					// WANT
						}
					} else {
						transaction.setTransactionStatus(TransactStatus.FAILED);// TODO REASON FOR FAILURE IF YOU WANT
					}

				} else {
					// Beneficiary is in some other bank
					if (customerAccount.getBalance() >= amount) {
						// debiting money from customer account
						accountService.debit(accountNumber, amount);
						// crediting money to beneficiary account by hitting another bank's API call
						Thread.sleep(5000);// mimicing API call
						// in real case scenario lookout for rollback request

						transaction.setTransactionStatus(TransactStatus.SUCCESS);

					} else {
						transaction.setTransactionStatus(TransactStatus.FAILED);// TODO REASON FOR FAILURE IF YOU WANT
					}
				}
			} else {
				transaction.setTransactionStatus(TransactStatus.FAILED);// TODO REASON FOR FAILURE IF YOU WANT
			}
		} catch (Exception e) {
			log.error("error in sending Money");
			e.printStackTrace();

		}
		return transaction;
	}

	public Transaction receiveMoney(Transaction transaction, String accountNumber, String beneficiaryAccountNumber,
			double amount) {
		log.info("TransactionService:receiveMoney");
		try {

		} catch (Exception e) {

		}
		return transaction;
	}

	public boolean depositCash(String accountNumber, double amount) {
		log.info("TransactionService:depositCash");
		try {

		} catch (Exception e) {

		}
		return false;
	}

	public static boolean withdrawCash(String accountNumber, double amount) {
		log.info("TransactionService:withdrawCash");
		try {

		} catch (Exception e) {

		}
		return false;
	}

}
