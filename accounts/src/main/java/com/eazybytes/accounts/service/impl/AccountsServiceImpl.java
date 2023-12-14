package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.model.dto.AccountsMsgDto;
import com.eazybytes.accounts.exceptions.EntityNotFoundException;
import com.eazybytes.accounts.utils.mapper.AccountsConstants;
import com.eazybytes.accounts.utils.mapper.CustomerMapper;
import com.eazybytes.accounts.model.dto.CustomerDto;
import com.eazybytes.accounts.model.entity.Accounts;
import com.eazybytes.accounts.model.entity.Customer;
import com.eazybytes.accounts.exceptions.EntityAlreadyExistsException;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private static final Logger log = LoggerFactory.getLogger(AccountsServiceImpl.class);

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private final StreamBridge streamBridge; // Spring-cloud-stream creates a bean of this type and injects it into this class


    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new EntityAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        Accounts savedAccount = accountsRepository.save(createNewAccount(savedCustomer));
        sendCommunication(savedAccount, savedCustomer);
    }

    @Override
    public boolean updateCommunicationStatus(Long accountNumber) {
        boolean isUpdated = false;
        if(accountNumber != null) {
            var accounts = accountsRepository.findById(accountNumber)
                    .orElseThrow(() -> new EntityNotFoundException("Accounts", "accountNumber", accountNumber.toString()));
        accounts.setCommunicationSw(true);
        accountsRepository.save(accounts);
        isUpdated = true;
        log.info("Account updated");
        }
        return isUpdated;
    }

    private void sendCommunication(Accounts account, Customer customer) {
        var accountsMsgDto = new AccountsMsgDto(account.getAccountNumber(), customer.getName(),
                customer.getEmail(), customer.getMobileNumber());
        log.info("Sending Communication request for the details: {}", accountsMsgDto);
        // below we specify the connection binding name, specified in the app.properties file
        // spring.cloud.stream.bindings.sendCommunication-out-0.destination=send-communication
        var result = streamBridge.send("sendCommunication-out-0", accountsMsgDto);
        log.info("Is the Communication request successfully triggered ? : {}", result);
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
}
