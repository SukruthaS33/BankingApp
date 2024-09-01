package com.sukrutha.bankingApp.entities;


import java.time.LocalDate;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import com.sukrutha.bankingApp.entities.EnumContainer.AccountType;


@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="account_number")
	private String account_number;
	@Enumerated(EnumType.STRING)
	@Column(name="account_type")
	private AccountType account_type;
	@ManyToOne
	@JoinColumn(name="branch_id")
	@Column(name="branch_id")
	private Branch branch_id;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer_id;
	@OneToMany(mappedBy="account")
	private List<Beneficiary> beneficiaries ;
	@Column(name="balance")
	@Min(value =0,message="minimum balance of 100 is required")
	private double balance;
	
	
	
	
	
	

}
