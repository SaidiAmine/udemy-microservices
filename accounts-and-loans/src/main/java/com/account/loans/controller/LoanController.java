package com.account.loans.controller;

import com.account.loans.models.Customer;
import com.account.loans.models.Loan;
import com.account.loans.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController {

    public LoanRepository loanRepository;

    @Autowired
    public LoanController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @PostMapping("/myLoans")
    public List<Loan> findLoanByCustomer(@RequestBody Customer customer) {
        return loanRepository.findByCustomerIdOrderByCreatedAtDesc(customer.getCustomerId());
    }
}
