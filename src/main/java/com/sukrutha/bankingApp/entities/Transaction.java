package com.sukrutha.bankingApp.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sukrutha.bankingApp.entities.EnumContainer.AccountType;
import com.sukrutha.bankingApp.entities.EnumContainer.TransactStatus;
import com.sukrutha.bankingApp.entities.EnumContainer.TransactionType;
import com.sukrutha.bankingApp.entities.EnumContainer.TransferType;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.CascadeType;
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
import lombok.ToString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@NotNull
	@Column(name = "transaction_id")
	private String transactionId;
	@JsonBackReference 
	@ManyToOne
	@JoinColumn(name = "customer_acct_id")
	private Account customerAccount;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "beneficiary_account_id")
	private Account beneficiaryAccount;
	@Column(name = "amount")
	@NotNull
	private double amount;
	@Column(name = "transaction_type")
	@NotNull
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	@NotNull
	@LastModifiedDate
	@Column(name = "transaction_date_time")
	private LocalDateTime transcationTime;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_status", length = 20)
	private TransactStatus transactionStatus;
	@NotNull
	@Column(name = "transfer_type")
	@Enumerated(EnumType.STRING)
	private TransferType transferType;
	@Transient
	private boolean debitOrCredit;// if true then debit
	
}
