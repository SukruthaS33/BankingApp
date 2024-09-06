package com.sukrutha.bankingApp.controllers;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sukrutha.bankingApp.entities.Beneficiary;
import com.sukrutha.bankingApp.services.BeneficiaryService;


@RestController
@RequestMapping("/api/v1/beneficiaries")
public class BeneficiaryController {


	@Autowired
	BeneficiaryService beneficiaryService;
	
//	@GetMapping("/{accountNumber}")
//	public ResponseEntity<List<Beneficiary>> getAllBeneficiaries(@PathVariable String accountNumber){
//		List<Beneficiary> beneficiaries;
//		try {
//			beneficiaries= beneficiaryService.getAllBeneficiaries(accountNumber);
//			return ResponseEntity.status(HttpStatus.OK).body(beneficiaries);
//			
//		}
//		catch(Exception e) {
//			e.printStackTrace()
//;		}
//	}
	
//	@PostMapping("/")//add beneficiary should be in accountController not in beneficiaryController
//	public ResponseEntity<Beneficiary>addBeneficiary(@RequestBody Beneficiary beneficiary){
//		
//		Map<Beneficiary,String> response;
//		try {
//			 beneficiary =beneficiaryService.addBeneficiary(beneficiary);
//			
//			return ResponseEntity.status(HttpStatus.OK).body(beneficiary);
//		}
//		
//		catch(Exception e){
//			e.printStackTrace();
//			response.put(beneficiary, "unable to add beneficiary please try again later");
//			
//					
//		}
//		
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//	}
	
	
//	@PostMapping("/delete/{beneficiaryId}")
//	public ResponseEntity<Beneficiary,String>deleteBeneficiary(@PathVariable String beneficiaryId){
//		boolean deleteStatus=false;
//		try {
//				deleteStatus = beneficiaryService.deleteBeneficiary(beneficiaryId);
//				return ResponseEntity.status(HttpStatus.OK).body("beneficiary deleted");
//		}
//		
//		catch(Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("unable to delete beneficiary, Please try again later");
//}
//	}
//	
	
	
		
	



}
