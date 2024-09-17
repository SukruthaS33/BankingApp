package com.sukrutha.bankingApp.entities;

public class EnumContainer {

    public enum AccountType {
        SAVINGS, CURRENT, SALARY,FIXED
    }

    
    public enum PhoneType{
    	HOME , OFFICE, MOBILE
    }
    
    
    public enum TransactionType{
    	NEFT,RTGS
    }
    
    public enum TransactStatus{
    	PENDING,SUCCESS,FAILED
    }
    
    public enum TransferType{
    	SEND_MONEY,RECEIVE_MONEY
    }
    
}