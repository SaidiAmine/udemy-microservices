package com.eazybytes.accounts.utils.mapper;

import com.eazybytes.accounts.model.dto.CustomerDto;
import com.eazybytes.accounts.model.entity.Customer;

public class CustomerMapper {


    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        return new CustomerDto(customer.getName(), customer.getEmail(), customer.getMobileNumber(), null);
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }


}
