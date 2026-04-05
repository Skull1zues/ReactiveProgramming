package com.soumya._stWebFluxApplication.tests.sec07;

import com.soumya._stWebFluxApplication.tests.sec07.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Map;

public class Lec04HeaderTest extends AbstractWebClient{

    private final WebClient client = createWebClient( b -> b.defaultHeader("caller-id", "order-service"));
    @Test
    public void getDefaultHeader() throws InterruptedException {

        this.client.get()
                .uri("/lec04/product/1")
                .retrieve()
                .bodyToMono(Product.class)
                .doOnNext(System.out::println)
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }

    @Test
    public void overrideHeader() throws InterruptedException {

        this.client.get()
                .uri("/lec04/product/1")
                .header("caller-id", "new-value")
                .retrieve()
                .bodyToMono(Product.class)
                .doOnNext(System.out::println)
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }
    @Test
    public void headerWithMap() throws InterruptedException {
        var map = Map.of("caller-id", "new-value",
                "some-key", "some-value");

        this.client.get()
                .uri("/lec04/product/1")
                .headers(httpHeaders -> httpHeaders.setAll(map))
                .retrieve()
                .bodyToMono(Product.class)
                .doOnNext(System.out::println)
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }


}
