package com.sukrutha.bankingApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sukrutha.bankingApp.Repositories.BranchRepository;
import com.sukrutha.bankingApp.entities.Branch;
import com.sukrutha.bankingApp.entities.Customer;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class BranchService {
	
	@Autowired
	BranchRepository branchRepository;
	
	
	


	public Branch getBranchById(String branchId) {
		try {
			Branch branch = branchRepository.findById(branchId)
					.orElseThrow(() -> new Exception("branch id is not found"));

			return branch;

		}
		catch(Exception e) {
			log.error("branch not found");
			e.printStackTrace();
		
			
		}
		return null;
	}
	
	
	

}
