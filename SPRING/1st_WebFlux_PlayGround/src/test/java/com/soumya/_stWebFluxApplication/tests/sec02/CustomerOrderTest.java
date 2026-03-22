package com.soumya._stWebFluxApplication.tests.sec02;

import com.soumya._stWebFluxApplication.sec02.repository.CustomerOrderRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
public class CustomerOrderTest {

    private static final Logger logger = LoggerFactory.getLogger(CustomerOrderTest.class);

    private final CustomerOrderRepository customerOrderRepository;

    @Autowired
    public CustomerOrderTest(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    @Test
    void productOrderCustomer() {
        customerOrderRepository.getProductOrderedByCustomer("mike")
                .doOnNext(c -> logger.info("{}", c))
                .as(StepVerifier::create)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    public void orderDetailsByProduct() {
        this.customerOrderRepository.getOrderDetailsByProduct("iphone 20")
                .doOnNext(c -> logger.info("{}", c))
                .as(StepVerifier::create)
                .expectNextCount(2)
                .expectComplete()
                .verify();
    }
}