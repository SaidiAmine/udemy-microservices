package com.account.loans.repository;

import com.account.loans.models.Loan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findByCustomerIdOrderByCreatedAtDesc(Long customerId);
}
