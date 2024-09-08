package com.sukrutha.bankingApp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admin")
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	@Id
	@Column(name = "admin_user_id")
	@NotBlank
	@NotNull
	private String adminUserId;
	@Column(name = "admin_password")
	@NotNull
	@NotBlank
	private String adminPassword;

}
