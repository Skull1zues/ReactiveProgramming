package com.soumya._stWebFluxApplication.tests.sec07;

import com.soumya._stWebFluxApplication.tests.sec07.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

public class Lec08BeararAuthTest extends AbstractWebClient{
    private final WebClient client = createWebClient
            (b ->
                    b.defaultHeaders(headers->
                            headers.setBearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")));
    @Test
    public void bearerAuth() throws InterruptedException {

        this.client.get()
                .uri("/lec08/product/1")
                .retrieve()
                .bodyToMono(Product.class)
                .doOnNext(System.out::println)
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }
}
