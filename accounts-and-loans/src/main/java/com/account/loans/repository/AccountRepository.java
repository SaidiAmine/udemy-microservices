package com.account.loans.repository;

import com.account.loans.models.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findAccountById(Long id);
}
