package com.sukrutha.bankingApp.entities;

public class EnumContainer {

    public enum AccountType {
        SAVINGS, CURRENT, SALARY,FIXED
    }

    
    public enum PhoneType{
    	HOME , OFFICE, MOBILE
    }
    
    
    public enum TransactionType{
    	DEPOSIT,WITHDRAW
    }
    
    public enum TransactStatus{
    	PENDING,SUCCESS,FAILED
    }
    
}