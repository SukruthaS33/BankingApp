package com.sukrutha.bankingApp.entities;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sukrutha.bankingApp.entities.EnumContainer.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "branch")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "branch_id")
	private String branchId;
	@NotNull
	@Column(name = "branch_name")
	private String branchName;
	@Embedded
	@Valid
	private Address branchAddress;
	@ManyToOne
	@JoinColumn(name = "bank_id")//this can be renamed as you wish to suit
	@JsonIgnore
	@NotNull
	@Embedded
	private Bank bank;
	@OneToMany(mappedBy = "branch")//owning side  branch owns accounts and this branch is a field in Accounts
	@JsonIgnore
	private List<Account> accounts;
	@NotNull
	@NotBlank
	@Column(name = "ifsc_code")
	private String ifscCode;

}
