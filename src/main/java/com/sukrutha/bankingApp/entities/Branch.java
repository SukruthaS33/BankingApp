package com.sukrutha.bankingApp.entities;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;


@Entity
@Table(name="branch")
public class Branch {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="branch_id")
	private String branchId;
	@NotNull
	@Column(name="branch_name")
	private String branchName;
	@Embedded
	private Address branchAddress;
	@ManyToOne
	@JoinColumn(name="bank_id")
	private Bank bank;
	@OneToMany(mappedBy="branch")
	private List<Account> accounts;
	
	
	
	
	}
