package com.sukrutha.bankingApp.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Embedded;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sukrutha.bankingApp.entities.EnumContainer.AccountType;

@Entity
@Table(name = "account")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	@Id
	@Column(name = "account_number")
	private String accountNumber;
	@Column(name = "account_type")
	@Enumerated(EnumType.STRING)
	@JsonIgnore
	private AccountType accountType;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "branch_id")//can be named as per wish
	private Branch branch;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private Customer customer;
	
	@ManyToMany
	@JoinTable(name = "account_beneficiary", joinColumns = @JoinColumn(name = "account_number"), inverseJoinColumns = @JoinColumn(name = "beneficiary_id"))
	@JsonIgnore
	private List<Beneficiary> beneficiaries;
	@Column(name = "balance", columnDefinition = "DECIMAL(10,2) DEFAULT '0.00'")
	@NotNull
	@Min(value = 0, message = "Minimum balance must be more than 0")
	private double balance;
	@OneToMany(mappedBy = "customerAccount")//always map it by field name
	@JsonIgnore
	private List<Transaction> transactions;
	@Column(name = "isActive", columnDefinition = "BOOLEAN DEFAULT false")
	@NotNull
	@JsonIgnore
	private boolean isActive;

}
