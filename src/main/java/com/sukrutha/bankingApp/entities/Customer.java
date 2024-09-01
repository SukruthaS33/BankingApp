package com.sukrutha.bankingApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Embedded;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Entity
@Table(name = "customer_details")

public class Customer {

	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.UUID)
	@NotNull
	@NotBlank
	private String customerId;
	@Column(name = "customer_name")
	@NotNull
	@NotBlank
	private String customerName;
	@Column(name = "customer_email")
	private String customerEmail;
	@NotNull
	@NotBlank
	@Column(name = "customer_password")
	private String customerPassword;
	@Min(value=10, message="Sorry you should be 10 years old to open an account. Go to child's bank")
	@Max(value=100, message="you are 100 years old!")
	private int age;
	@Column(name = "customer_DOB")
	@NotNull
	@NotBlank
	private LocalDate customerDOB;
	@Embedded
	private String customerAddress;
	@Embedded
	private PhoneNumber customerPhoneNumber;
	@Column(name = "created_at")
	@NotNull
	@NotBlank
	private LocalDate createdOn;
	@Column(name = "last_updated_at")
	@NotNull
	@NotBlank
	private LocalTime LastUpdatedAt;

}
