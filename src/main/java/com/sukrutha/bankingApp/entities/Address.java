package com.sukrutha.bankingApp.entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.sukrutha.bankingApp.entities.EnumContainer.AccountType;

import jakarta.persistence.Column;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	@NotNull
	@Column(name = "buildingno")
	private String houseNumber;
	@NotNull
	@Column(name = "street")
	private String street;
	@NotNull
	@Column(name = "locality")
	private String locality;
	@NotNull
	@Column(name = "city")
	private String city;
	@NotNull
	@Column(name = "zipcode")
	private String zipcode;

}
