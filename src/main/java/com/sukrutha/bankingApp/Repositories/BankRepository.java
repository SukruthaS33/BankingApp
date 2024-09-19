package com.sukrutha.bankingApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sukrutha.bankingApp.entities.Bank;

@Repository
public interface BankRepository  extends JpaRepository<Bank,String>{

}
