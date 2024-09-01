package com.sukrutha.bankingApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sukrutha.bankingApp.entities.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

}
