package com.eazybytes.accounts.service;

import com.eazybytes.accounts.model.dto.CustomerDto;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);
    boolean updateCommunicationStatus(Long accountNumber);

}
