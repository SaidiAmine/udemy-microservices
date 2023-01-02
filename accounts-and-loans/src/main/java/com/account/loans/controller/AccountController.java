package com.account.loans.controller;

import com.account.loans.config.ConfigurationService;
import com.account.loans.models.Account;
import com.account.loans.models.Customer;
import com.account.loans.models.Properties;
import com.account.loans.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AccountController {

    private AccountRepository accountRepository;
    private final ConfigurationService accountsConfig;

    @Autowired
    public AccountController(AccountRepository accountRepository, ConfigurationService accountsConfig) {
        this.accountRepository = accountRepository;
        this.accountsConfig = accountsConfig;
    }

    @PostMapping("/myAccount")
    public Account findAccount(@RequestBody Customer customer) {
        return accountRepository.findAccountById(customer.getCustomerId()).orElseGet(null);
    }

    @GetMapping("/account/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accountsConfig.getMsg(), accountsConfig.getBuildVersion(),
                accountsConfig.getMailDetails(), accountsConfig.getActiveBranches());
        return ow.writeValueAsString(properties);
    }
}
