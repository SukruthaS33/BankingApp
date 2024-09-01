package com.sukrutha.bankingApp.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;



@Entity
@Table(name="beneficiary")
public class Beneficiary {
	
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@NotNull
	@Column(name="beneficiary_id")
	private String beneficiaryId;
	@NotNull
	@Column(name="beneficiary_name")
	private String beneficiaryName;
	@NotNull
	@Column(name="beneficiary_acct_no")
	private String beneficiaryAcctNumber;
	@NotNull
	@Column(name="beneficiary_bank_nm")
	private String beneficiaryBankName;
	@NotNull
	@Column(name="beneficiary_inbank")
	private boolean beneficiaryIsInBank;
	@NotNull
	@Column(name="created_on")
	private LocalDate createdDate;
	@NotNull
	@Column(name="last_updated_ts")
	private LocalTime LastUpdatedAt;
	
	
	

	
	
	

}
