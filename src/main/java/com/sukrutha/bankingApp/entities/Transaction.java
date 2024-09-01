package com.sukrutha.bankingApp.entities;

import java.time.LocalDateTime;

import com.sukrutha.bankingApp.entities.EnumContainer.StatusIn;
import com.sukrutha.bankingApp.entities.EnumContainer.TransactionType;
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
import jakarta.persistence.GenerationType;

@Entity
@Table(name="transactions")
public class Transaction {
	
	@GeneratedValue(strategy=GenerationType.UUID)
	@NotNull
	@Column(name="transaction_id")
	private String transactionId;
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account sourceAccountId;
	@ManyToOne
	@JoinColumn(name="target_account_id")
	private Beneficiary targetAccountId;
	@NotNull
	@Min(value=1, message ="Please add amount to transfer")
	@Column(name="amount")
	private double Amount;
	@NotNull
	private TransactionType transactionType;
	@NotNull
	@Column(name="last_updated_ts")
	private LocalDateTime transcationTime;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="transaction_status")
	private StatusIn transactionStatus;
	
	

}
