package com.sukrutha.bankingApp.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sukrutha.bankingApp.entities.Transaction;
import com.sukrutha.bankingApp.entities.EnumContainer.TransactStatus;
import com.sukrutha.bankingApp.entities.EnumContainer.TransactionType;
import com.sukrutha.bankingApp.services.TransactionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/transactions")
@Slf4j
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@GetMapping("/")
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		log.info("TransactionController::getAllTransactions");
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			transactions = transactionService.getAllTransactions();
			return ResponseEntity.status(HttpStatus.OK).body(transactions);

		}

		catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(transactions);

	}

	@GetMapping("/{accountNumber}/history")
	public ResponseEntity<List<Transaction>> getAccountTransactionHistory(@PathVariable String accountNumber) {
		log.info("TransactionController::getAccountTransactionHistory");

		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			transactions = transactionService.getAccountTransactionHistory(accountNumber);
			return ResponseEntity.status(HttpStatus.OK).body(transactions);

		}

		catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(transactions);

	}

	@PostMapping("/{accountNumber}/send")
	public ResponseEntity<Transaction> sendMoney(@PathVariable String accountNumber,
			@RequestParam String beneficiaryAccountNumber, @RequestParam double amount,
			@RequestParam String transactionType) {
		log.info("TransactionController::sendMoney");
		Transaction transaction = new Transaction();
		transaction.setTransactionStatus(TransactStatus.PENDING);
		TransactionType type = TransactionType.valueOf(transactionType.toUpperCase());
		if (type != null)
			transaction.setTransactionType(type);
		try {
			transaction = transactionService.sendMoney(transaction, accountNumber, beneficiaryAccountNumber, amount);
			return ResponseEntity.status(HttpStatus.OK).body(transaction);
		} catch (Exception e) {
			e.printStackTrace();

		}
		transaction.setTransactionStatus(TransactStatus.FAILED);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(transaction);
	}

	@PostMapping("{accountNumber}/receive")
	public ResponseEntity<Transaction> receiveMoney(@PathVariable String accountNumber,
			@RequestParam String beneficiaryAccountNumber, @RequestParam double amount,
			@RequestParam String transactionType) {
		log.info("TransactionController::receiveMoney");
		Transaction transaction = new Transaction();
		transaction.setTransactionStatus(TransactStatus.PENDING);
		TransactionType type = TransactionType.valueOf(transactionType.toUpperCase());
		if (type != null)
			transaction.setTransactionType(type);
		try {
			transaction = transactionService.receiveMoney(transaction, accountNumber, beneficiaryAccountNumber, amount);
			return ResponseEntity.status(HttpStatus.OK).body(transaction);
		} catch (Exception e) {
			e.printStackTrace();

		}
		transaction.setTransactionStatus(TransactStatus.FAILED);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(transaction);
	}

	@PostMapping("/{accountNumber}/deposit")
	public ResponseEntity<Boolean> depositCash(@PathVariable String accountNumber, @RequestParam double amount) {
		log.info("TransactionController:depositCash");
		boolean depositStatus = false;

		try {
			depositStatus = transactionService.depositCash(accountNumber, amount);
			return ResponseEntity.status(HttpStatus.OK).body(depositStatus);
		}

		catch (Exception e) {
			e.printStackTrace();

		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(depositStatus);

	}

//	@PostMapping("/{accountNumber}/withdraw")
//	public ResponseEntity<Boolean> withdrawCash(@PathVariable String accountNumber, @RequestParam double amount) {
//		log.info("TransactionController:withdrawCash");
//		boolean withdrawCashStatus = false;
//		try {
//
//			withdrawCashStatus = TransactionService.withdrawCash(accountNumber, amount);
//			return ResponseEntity.status(HttpStatus.OK).body(withdrawCashStatus);
//		}
//
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(withdrawCashStatus);
//	}

}