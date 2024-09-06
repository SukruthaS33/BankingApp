package com.sukrutha.bankingApp.controllers;

import com.sukrutha.bankingApp.entities.Customer;

public class CustomerResponse {
    private Customer customer;
    private String message;
   

    // Constructors
    public CustomerResponse(Customer customer, String message) {
        this.customer = customer;
        this.message = message;
       
    }

    // Getters and setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

  
}
