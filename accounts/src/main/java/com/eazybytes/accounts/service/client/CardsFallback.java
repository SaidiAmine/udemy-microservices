package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.model.entity.Cards;
import com.eazybytes.accounts.model.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardsFallback implements CardsFeignClient {
    private static final Logger log = LoggerFactory.getLogger(CardsFallback.class);

    @Override
    public List<Cards> getCardDetails(Customer customer) {
        log.info("CARDS FALLBACK METHOD");
        return List.of(new Cards()); // Example of returning a default list with 1 element of Cards if the
        // Cards microservice fails to return a response to the Accounts microservice
        // this class is defined as a fallback method on the CardsFeignClient
    }
}
