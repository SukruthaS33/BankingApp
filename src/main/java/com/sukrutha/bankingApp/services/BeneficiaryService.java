package com.sukrutha.bankingApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.sukrutha.bankingApp.Repositories.AccountRepository;
import com.sukrutha.bankingApp.Repositories.BeneficiaryRepository;
import com.sukrutha.bankingApp.controllers.BeneficiaryController;
import com.sukrutha.bankingApp.entities.Account;
import com.sukrutha.bankingApp.entities.Beneficiary;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BeneficiaryService {

	@Autowired
	BeneficiaryRepository beneficiaryRepository;

	@Autowired
	@Lazy
	AccountService accountService;
//	
//	public <Beneficiary> getBeneficiariesLinkedToACustomerAccount(String accountNumber){
//		
//		try {
//			return null;
//		}
//		catch(Exception e) {
//			
//		}
//	}

	public Beneficiary addBeneficiary(Beneficiary beneficiary) {
		log.info("BeneficiaryService::addBeneficiary");
		// adds beneficiary to beneficiary table
		Beneficiary savedBeneficiary = null;
		try {
			// check if beneficiary already exists in table
			if(this.validateBeneficiaryDetails(beneficiary)) {
				if (this.getBeneficiaryByBeneficiaryAccountNumber(beneficiary.getBeneficiaryAcctNumber()) == null) {
					// logic to check if same bank /account number valid can be added here

					savedBeneficiary = beneficiaryRepository.save(beneficiary);

				}
				else {
					log.error("beneficiary already exists");
				}
			}
			
			else {
				log.error("invalid credentials of beneficiary");
			}
			

		} catch (Exception e) {
			log.error("failed to add beneficiary");
			e.printStackTrace();

		}

		return savedBeneficiary;
	}

	public Beneficiary getBeneficiaryById(String beneficiaryId) {

		log.info("BeneficiaryService::getBeneficiaryById");
		Beneficiary beneficiary = null;
		try {
			beneficiary = beneficiaryRepository.findById(beneficiaryId)
					.orElseThrow(() -> new Exception("beneficiary is not found"));
		} catch (Exception e) {
			log.error("error getting beneficiary");
			e.printStackTrace();

		}
		return beneficiary;
	}

	public Beneficiary getBeneficiaryByBeneficiaryAccountNumber(String beneficiaryAcctNumber) {

		log.info("BeneficiaryService::getBeneficiaryByBeneficiaryAccountNumber");
		Beneficiary beneficiary = null;
		try {
			beneficiary = beneficiaryRepository.findByBeneficiaryAcctNumber(beneficiaryAcctNumber)
					.orElseThrow(() -> new Exception("beneficiary is not found"));
		} catch (Exception e) {
			log.error("error getting beneficiary");
			e.printStackTrace();

		}
		return beneficiary;
	}

	public String updateBeneficiary(Beneficiary beneficiary) {
		log.info("BeneficiaryService updateBeneficiary");
		try {

			// updating existing account

			beneficiary = beneficiaryRepository.save(beneficiary);
			return beneficiary.getBeneficiaryId();

		} catch (IllegalArgumentException e) {

			e.printStackTrace();
			log.error("exception while updating beneficiary");
		}

		catch (Exception e) {

			e.printStackTrace();
			log.error("exception while updating beneficiary");
		}
		return beneficiary.getBeneficiaryId();
	}

	public boolean deleteBeneficiary(Beneficiary beneficiary) {
		log.info("BeneficiaryService::deleteBeneficiary");
		try {

		} catch (Exception e) {

		}
		return false;
	}

	public boolean validateBeneficiaryDetails(Beneficiary beneficiary) {
		boolean beneficiaryIsACustomer = false;
		try {
			String beneficiaryName = beneficiary.getBeneficiaryName();
			String beneficiaryAccountNumber = beneficiary.getBeneficiaryAcctNumber();
			String beneficiaryBankName = beneficiary.getBeneficiaryBankName();
			String beneficiaryIFSCCode = beneficiary.getBeneficiaryIfscCode();

			// assuming beneficiary is in our bank just check if beneficiary details are
			// valid
			Account beneficiaryAccount = accountService.getAccountByAccountNumber(beneficiaryAccountNumber);// checking
																											// if
																											// account
																											// is
																											// present

			if (beneficiaryAccount != null) {
				if (beneficiaryAccount.getCustomer().getCustomerName().equalsIgnoreCase(beneficiaryName)) {
					return true;
				}
			}

		} catch (Exception e) {
			log.error("error in validating added beneficiary");
			e.printStackTrace();

		}

		return false;

	}

}
