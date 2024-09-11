package com.sukrutha.bankingApp.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sukrutha.bankingApp.entities.Account;
import com.sukrutha.bankingApp.entities.Beneficiary;
import com.sukrutha.bankingApp.entities.Customer;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	public List<Account> findByCustomer(Customer customer);
	//below is in correct because ..
//	@Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM account_beneficiary a WHERE a.account_number = :accountNumber AND a.beneficiary_id = :beneficiaryId")
//	public boolean existsBeneficiaryInAccount2(@Param("accountNumber") String accountNumber,
//			@Param("beneficiaryId") String beneficiaryId);
//	
//	@Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM account_beneficiary a WHERE a.account_number = :account AND a.beneficiary_id = :beneficiary")
//	public boolean existsBeneficiaryInAccount3(@Param("account") Account account,
//			@Param("beneficiary") Beneficiary beneficiary);
	
	@Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Account a JOIN a.beneficiaries b WHERE a = :account AND b = :beneficiary")
	public boolean existsBeneficiaryInAccount(@Param("account") Account account,
	                                          @Param("beneficiary") Beneficiary beneficiary);


}
