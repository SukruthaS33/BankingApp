package com.sukrutha.bankingApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;

@Entity
@Table(name = "customer")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Customer {

	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private String customerId;
	@Column(name = "customer_name")
	@NotNull
	@NotBlank
	private String customerName;
	@Column(name = "customer_email")
	@Email(message = "given email Id is not valid")
	private String customerEmail;
	@Column(name = "customer_password")
	@NotNull
	@NotBlank
	private String customerPassword;
	@Min(value = 10, message = "Sorry you should be 10 years old to open an account. Go to child's bank")
	@Max(value = 100, message = "you are 100 years old!")
	private int age;
	@Valid
	@Embedded
	private Address customerAddress;
	@Valid
	@Embedded
	private PhoneNumber customerPhoneNumber;
	@OneToMany(mappedBy = "customer")
	private List<Account> accounts;
	@Column(name = "created_at")
	@CreatedDate
	private LocalDateTime createdAt;
	@Column(name = "last_updated_at")
	@LastModifiedDate
	private LocalDateTime LastUpdatedAt;
	@Column(name = "isActive", columnDefinition = "BOOLEAN DEFAULT false")
	@NotNull
	private boolean isActive;
	

}
