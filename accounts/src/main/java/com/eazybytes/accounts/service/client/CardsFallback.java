package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.model.Cards;
import com.eazybytes.accounts.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardsFallback implements CardsFeignClient {
    @Override
    public List<Cards> getCardDetails(Customer customer) {
        System.out.println("CARDS FALLBACK METHOD");
        return List.of(new Cards()); // Example of returning a default list with 1 element of Cards if the
        // Cards microservice fails to return a response to the Accounts microservice
        // this class is defined as a fallback method on the CardsFeignClient
    }
}
