package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.model.Loans;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoansFallback implements LoansFeignClient {
    @Override
    public List<Loans> getLoansDetails(Customer customer) {
        return null;
    }
}
