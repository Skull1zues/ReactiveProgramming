package com.soumya._stWebFluxApplication.tests.sec07;

import com.soumya._stWebFluxApplication.tests.sec07.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

public class Lec07BasicAuthTest extends AbstractWebClient{
    private final WebClient client = createWebClient
            (b ->
                    b.defaultHeaders(headers->
                            headers.setBasicAuth("java","secret")));
    @Test
    public void basicAuth() throws InterruptedException {

        this.client.get()
                .uri("/lec07/product/1")
                .retrieve()
                .bodyToMono(Product.class)
                .doOnNext(System.out::println)
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }
}
