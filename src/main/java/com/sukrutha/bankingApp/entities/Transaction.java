package com.sukrutha.bankingApp.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.sukrutha.bankingApp.entities.EnumContainer.AccountType;
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
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@NotNull
	@Column(name = "transaction_id")
	private String transactionId;
	@ManyToOne
	@JoinColumn(name = "source_acct_id")
	private Account sourceAccountId;
	@ManyToOne
	@JoinColumn(name = "target_account_id")
	private Beneficiary targetAccountId;
	@Column(name = "amount")
	@NotNull
	@Min(value = 1, message = "Please add amount to transfer")
	private double Amount;
	@Column(name = "transaction_type")
	@NotNull
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	@NotNull
	@Column(name = "transaction_date_time")
	private LocalDateTime transcationTime;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_status")
	private StatusIn transactionStatus;

}
