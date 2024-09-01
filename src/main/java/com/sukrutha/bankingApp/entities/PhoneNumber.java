package com.sukrutha.bankingApp.entities;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import com.sukrutha.bankingApp.entities.EnumContainer.PhoneType;


@Embeddable
public class PhoneNumber {
	
	@NotNull
	
	private Map<Integer,String> extension;
	@NotNull
	private String phonenumber;
	@NotNull
	@Enumerated(EnumType.STRING)
	private PhoneType phoneType;
	
	

}
