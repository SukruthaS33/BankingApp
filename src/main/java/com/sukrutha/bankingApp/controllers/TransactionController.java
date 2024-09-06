//package com.sukrutha.bankingApp.controllers;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sukrutha.bankingApp.entities.Transaction;
//import com.sukrutha.bankingApp.services.TransactionService;
//
//@RestController
//@RequestMapping("/api/v1/transactions")
//public class TransactionController {
//	
//	
//	@Autowired
//	TransactionService transactionService;
//	
//	@GetMapping("/{accountNumber}/history")
//	public ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable String accountNumber){
//		
//		List<Transaction> transactions;
//		try {
//			transactions=transactionService.findAllTransactionsByAccountNumber(accountNumber);
//		return ResponseEntity.status(HttpStatus.OK).body(transactions);
//		
//		}
//		
//		catch(Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(transactions);
//		}
//		
//		
//	}
//	
//	
//	
//	
//	
//	@PostMapping("/{accountNumber}/send")
//	public ResponseEntity<Boolean>sendMoney(@PathVariable String accountNumber, @RequestParam String beneficiaryAccountNumber, @RequestParam double amount){
//		
//		boolean sendStatus=false;
//		try {
//		sendStatus =transactionService.sendMoney(accountNumber, beneficiaryAccountNumber, amount);
//		ResponseEntity.status(HttpStatus.OK).body(sendStatus);
//		}
//		catch(Exception e) {
//		e.printStackTrace();
//		ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(sendStatus);
//		}
//		}
//}
//
////
////	public ResponseEntity<Boolean> receiveMoney(@PathVariable String accountNumber,beneficiaryAccountNumber,double amount){
////		double receiveStatus= 0;
////			try {
////				receiveStatus = transactionService.receiveMoney(accountNumber, beneficiaryAccountNumber,amount);
////				return ResponseEntity.status(HttpStatus.OK).body()
////			}
////			catch(Exception e) {
////				
////				e.printStackTrace();
////				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body();
////				
////			}
////	}
////
//
//
//	@PostMapping("/{accountNumber}/deposit")
//	public ResponsEntity<Boolean> depositCash(@PathVariable String accountNumber, @RequetParam double amount){
//		boolean depositStatus =false;
//		
//		try {
//			depositStatus = transactionService.depositCash(accountNumber,amount);
//			return ResponseEntity.status(HttpStatus.OK).body(depositStatus);
//		}
//		
//		catch(Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(depositStatus);
//		}
//		
//	}
//	
//	
//	@PostMapping("/{accountNumber}/withdraw")
//	public ResponseEntity<Double> withdraw(@PathVariable String accountNumber, @RequestParam double amount){
//		
//		double withdraw =0;
//		try {
//			
//			withdraw =TransactionService.withdraw(accountNumber, amount);
//			return ResponseEntity.status(HttpStatus.OK).body(withdraw);
//		}
//		
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//	}