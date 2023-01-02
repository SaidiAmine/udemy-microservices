package com.account.loans.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString
@Table(name = "accounts")
public class Account {
    @Column(name = "customer_id")
    private Long id;
    @Column(name="account_number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long accountNumber;
    @Column(name="account_type")
    private String accountType;
    @Column(name = "branch_address")
    private String branchAddress;
    @Column(name = "create_dt")
    private LocalDate createDt;

}
