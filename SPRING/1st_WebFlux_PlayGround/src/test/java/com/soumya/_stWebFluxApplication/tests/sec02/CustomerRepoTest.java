package com.soumya._stWebFluxApplication.tests.sec02;

import com.soumya._stWebFluxApplication.sec02.entity.Customer;
import com.soumya._stWebFluxApplication.sec02.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.time.Duration;

public class CustomerRepoTest extends AbstractTest{
    private static final Logger logger = LoggerFactory.getLogger(CustomerRepoTest.class);

    @Autowired
    private CustomerRepository customerRepo;

    @Test
    public void findAll() {
        this.customerRepo.findAll()
                .doOnNext(c ->logger.info("{}",c))
                .as(StepVerifier::create)
                .expectNextCount(10)
                .verifyComplete();
    }


    @Test
    public void findById() {
        StepVerifier.create(customerRepo.findById(2))
                .assertNext(c -> Assertions.assertEquals("mike", c.getName()))
                .expectComplete()
                .verify();

    }

    @Test
    public void insertAndDelete() {
        // insert
        var customer = new Customer();
        customer.setEmail("abc@hjg.com");
        customer.setName("marshal");
        this.customerRepo.save(customer)
                .doOnNext(c->logger.info("{}",c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertNotNull(c.getId()))
                .expectComplete()
                .verify();

        this.customerRepo.count()
                .as(StepVerifier::create)
                .expectNext(11L)
                .expectComplete()
                .verify();

        this.customerRepo.deleteById(11)
                .then(this.customerRepo.count())
                .as(StepVerifier::create)
                .expectNext(10L)
                .expectComplete()
                .verify();
    }


    @Test
    public void update() {
        this.customerRepo.findByName("ethan")
                .doOnNext(c -> c.setName("Soumya"))
                .flatMap(c-> this.customerRepo.save(c))
                .doOnNext(c->logger.info("{}",c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertNotNull(c.getId()))
                .expectComplete()
                .verify();

    }
}
