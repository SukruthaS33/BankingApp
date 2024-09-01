package com.sukrutha.bankingApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sukrutha.bankingApp.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {

}
