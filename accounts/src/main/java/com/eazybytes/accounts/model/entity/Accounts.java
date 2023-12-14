package com.eazybytes.accounts.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Accounts {

	@Column(name = "customer_id")
	private int customerId;
	@Column(name="account_number")
	@Id
	private long accountNumber;
	@Column(name="account_type")
	private String accountType;
	@Column(name = "branch_address")
	private String branchAddress;
	@Column(name = "created_at")
	private LocalDate createdAt;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "updated_at")
	private LocalDate updatedAt;
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "communication_sw")
	private boolean communicationSw;
	
}
