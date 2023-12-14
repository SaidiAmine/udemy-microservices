package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.model.entity.Customer;
import com.eazybytes.accounts.model.entity.Loans;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoansFallback implements LoansFeignClient {
    @Override
    public List<Loans> getLoansDetails(Customer customer) {
        return null;
    }
}
