package com.sukrutha.bankingApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sukrutha.bankingApp.entities.Beneficiary;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary,String> {

}
