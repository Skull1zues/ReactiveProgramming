package com.soumya._stWebFluxApplication.tests.sec02;

import com.soumya._stWebFluxApplication.sec01.Product;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.test.StepVerifier;

public class DataBaseClientTest extends AbstractTest{
    public static final Logger logger= LoggerFactory.getLogger(DataBaseClientTest.class);

    @Autowired
    private DatabaseClient databaseClient;

    @Test
    public void orderDetailsByProduct(){
        this.databaseClient.sql("select * from product where product_id=:productId")
                .bind("productId",1)
                .mapProperties(Product.class)
                .all()
                .doOnNext(c ->logger.info(c.toString()))
                .as (StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }
}
