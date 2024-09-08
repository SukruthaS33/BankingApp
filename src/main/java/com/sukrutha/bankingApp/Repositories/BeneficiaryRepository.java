package com.sukrutha.bankingApp.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sukrutha.bankingApp.entities.Beneficiary;
import com.sukrutha.bankingApp.entities.Customer;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary,String> {
	
	
	public Optional<Beneficiary> findByBeneficiaryAcctNumber(String beneficiaryAcctNumber);

}
