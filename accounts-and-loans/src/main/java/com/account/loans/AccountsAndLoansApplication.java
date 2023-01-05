package com.account.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScans({@ComponentScan("com.account.loans.controller")})
/*The @ComponentScan annotation is used in Spring to specify the packages
that should be scanned for annotated components. It is used to create a Bean Definition Scanner,
which scans the specified packages for classes annotated with @Component, @Service, @Repository, or @Controller,
and registers them as beans in the Spring application context.
 */
@EnableJpaRepositories("com.account.loans.repository")
/*
* When you use @EnableJpaRepositories, Spring will scan the
* specified packages for repository interfaces that are annotated
* with @Repository, and will create a bean for each repository interface
* that it finds. This allows you to use the repository interfaces in your
* application to access data from a database or other data store.
* */
@EntityScan("com.account.loans.models")
/*
* The @EntityScan annotation is used to configure the EntityScanner,
* which scans the specified packages for classes annotated with @Entity,
* and registers them as entities in the JPA persistence unit. It is typically used in
* combination with the @EnableJpaRepositories annotation to enable the creation of repository
* beans from repository interfaces.
* */
@RefreshScope
/*
* The @RefreshScope annotation is used in Spring to indicate that the beans or components annotated
* with it should be eligible for refresh at runtime. This means that the bean or component can be
* updated or re-initialized with new configuration values without requiring a full application restart.
* */
public class AccountsAndLoansApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsAndLoansApplication.class, args);
    }

}
