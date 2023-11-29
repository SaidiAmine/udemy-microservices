package com.eaztbytes.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/myAccountFallback")
    public Mono<String> myAccount() {
        return Mono.just("An error occurred please try again later.");
    }
}
