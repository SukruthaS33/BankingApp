package com.sukrutha.bankingApp.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sukrutha.bankingApp.entities.Account;
import com.sukrutha.bankingApp.entities.Customer;

public interface AccountRepository extends JpaRepository<Account, String> {

	public List<Account> findByCustomer(Customer customer);

	@Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM account_beneficiary a WHERE a.account_number = :accountNumber AND a.beneficiary_id = :beneficiary_id")
	public boolean existsBeneficiaryInAccount(@Param("accountNumber") String accountNumber,
			@Param("beneficiaryId") String beneficiaryId);

}
