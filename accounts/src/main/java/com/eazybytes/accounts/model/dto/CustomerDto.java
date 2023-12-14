package com.eazybytes.accounts.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDto {

    private String name;
    private String email;
    private String mobileNumber;
    private AccountsDto accountsDto;
}