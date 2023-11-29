package com.eaztbytes.gatewayserver;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator udemyCustomRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/udemy-ms/accounts/**") //define the path in which to add fitlers/ redefine path
						.filters(f -> f.rewritePath("/udemy-ms/accounts/(?<segment>.*)", "/${segment}")// extract te segment and call it directly in the next method in the desired microservice
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())// Add response header to give approx time consumed for the request
//								.circuitBreaker(config -> config.setName("accountsCircuitBreaker") // define a circuitbreaker on the api
//										.setFallbackUri("forward:/myAccountFallback")) // define the fallback for circuit breaker, calls an other api check class FallbackController
//										this is on gateway level, we can defin circuit breaker on specific MS on the feign client
						)
						.uri("lb://ACCOUNTS")) // call load balancer on the ACCOUNTS microservice
				.route(p -> p.path("/udemy-ms/loans/**")
						.filters(f -> f.rewritePath("/udemy-ms/loans/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://LOANS"))
				.route(p -> p.path("/udemy-ms/cards/**")
						.filters(f -> f.rewritePath("/udemy-ms/cards/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://CARDS"))
				.build();
	}
//	@Autowired
//	private TokenRelayGatewayFilterFactory filterFactory;
//
//	@Bean
//	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//	    return builder.routes()
//	        .route(p -> p
//	            .path("/eazybank/accounts/**")
//	            .filters(f -> f.filters(filterFactory.apply())
//					.rewritePath("/eazybank/accounts/(?<segment>.*)","/${segment}")
//					.removeRequestHeader("Cookie"))
//	            .uri("lb://ACCOUNTS")).
//	        route(p -> p
//		            .path("/eazybank/loans/**")
//					.filters(f -> f.filters(filterFactory.apply())
//							.rewritePath("/eazybank/loans/(?<segment>.*)","/${segment}")
//							.removeRequestHeader("Cookie"))
//		            .uri("lb://LOANS")).
//	        route(p -> p
//		            .path("/eazybank/cards/**")
//					.filters(f -> f.filters(filterFactory.apply())
//							.rewritePath("/eazybank/cards/(?<segment>.*)","/${segment}")
//							.removeRequestHeader("Cookie"))
//		            .uri("lb://CARDS")).build();
//	}

}
