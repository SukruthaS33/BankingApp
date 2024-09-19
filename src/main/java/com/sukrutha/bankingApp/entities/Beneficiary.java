package com.sukrutha.bankingApp.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sukrutha.bankingApp.entities.EnumContainer.AccountType;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "beneficiary")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Beneficiary {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "beneficiary_id")
	@NotNull
	private String beneficiaryId;
	@JsonIgnore//added to get list of beneficiaries associated to an account otherwise accounts related to particular beneficiary was also coming
	@ManyToMany(mappedBy = "beneficiaries")
	private List<Account> account;
	@Column(name = "beneficiary_name")
	@NotNull
	private String beneficiaryName;
	@Column(name = "beneficiary_acct_no")
	@NotNull
	private String beneficiaryAcctNumber;
	@Column(name = "beneficiary_bank_nm")
	@NotNull
	private String beneficiaryBankName;
	@NotNull
	@NotBlank
	@Column(name = "beneficiary_ifsc_code")
	private String beneficiaryIfscCode;
	@Column(name = "isActive", columnDefinition = "BOOLEAN DEFAULT true")
	private boolean isActive;
	@Column(name = "createdAt")
	private LocalDateTime createdAt;
	@Column(name = "last_updated_ts")
	@LastModifiedDate
	private LocalDateTime lastUpdatedAt;
	

}
