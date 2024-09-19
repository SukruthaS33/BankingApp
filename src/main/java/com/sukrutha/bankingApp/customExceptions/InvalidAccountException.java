package com.sukrutha.bankingApp.customExceptions;

public class InvalidAccountException extends RuntimeException {
	
	public InvalidAccountException(String str) {
		super(str);
	}

}
