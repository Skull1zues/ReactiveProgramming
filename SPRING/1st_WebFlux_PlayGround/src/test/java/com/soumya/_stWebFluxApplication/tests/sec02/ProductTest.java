package com.soumya._stWebFluxApplication.tests.sec02;

import com.soumya._stWebFluxApplication.sec02.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.test.StepVerifier;

public class ProductTest extends AbstractTest{
    @Autowired
    ProductRepository productRepository;

    Logger logger = LoggerFactory.getLogger(ProductTest.class);

    @Test
    public void productInBetweenPriceTest(){
        this.productRepository.findByPriceBetween(750,1000)
                .doOnNext(p -> logger.info(p.toString()) )
                .as(StepVerifier::create)
                .expectNextCount(3)
                .expectComplete()
                .verify();
    }

    @Test
    public void productPageableTest(){
        Pageable pageable = PageRequest.of(1, 3).withSort(Sort.by("price"));
        this.productRepository.findAllBy(pageable)
                .doOnNext(p -> logger.info(p.toString()) )
                .as(StepVerifier::create)
                .expectNextCount(3)
                .expectComplete()
                .verify();
    }
}
