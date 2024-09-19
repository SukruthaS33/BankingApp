package com.sukrutha.bankingApp.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

import com.sukrutha.bankingApp.entities.EnumContainer.AccountType;
import com.sukrutha.bankingApp.entities.EnumContainer.PhoneType;


@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber {
	@Column(name="extension")
	@NotNull
	private int extension;
	@Column(name="ph_no")
	@NotNull
	private String phonenumber;
	@Column(name="ph_type")
	@NotNull
	@Enumerated(EnumType.STRING)
	private PhoneType phoneType;
	
	

}
