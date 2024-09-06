package com.sukrutha.bankingApp.entities;

import java.util.List;

import com.sukrutha.bankingApp.entities.EnumContainer.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="bank")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bank{

  @Id
  @GeneratedValue(strategy=GenerationType.UUID )
  @Column(name="bank_id")
  private String bankId;
  @Column(name="bank_Name")
  @NotNull(message="bank name can not be null")
  private String bankName;
  @OneToMany(mappedBy="bank")
  private List<Branch> branches;
  
}