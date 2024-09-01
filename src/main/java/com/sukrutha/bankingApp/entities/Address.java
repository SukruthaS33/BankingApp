package com.sukrutha.bankingApp.entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;


@Embeddable
public class Address {
	
	
	
	
	
	@NotNull
	@Column(name="houseno")
	private String houseNumber;
	@NotNull
	@Column(name="street")
	private String street;
	@NotNull
	@Column(name="locality")
	private String locality;
	@NotNull
	@Column(name="city")
	private String city;
	@NotNull
	@Column(name="zipcode")
	private String zipcode;
	
	
	

}
