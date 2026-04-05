package com.soumya._stWebFluxApplication.tests.sec07;

import com.soumya._stWebFluxApplication.tests.sec07.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec03PostTest extends AbstractWebClient{

    private final WebClient client = createWebClient();
    @Test
    public void PostBodyValue() throws InterruptedException {

        this.client.post()
                .uri("/lec03/product")
                .bodyValue(new Product(null, "iphone",1000))
                .retrieve()
                .bodyToMono(Product.class)
                .doOnNext(System.out::println)
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }

    @Test
    public void PostBody() throws InterruptedException {
        var mono = Mono.fromSupplier(() -> new Product(null, "iphone",1000))
                .delayElement(Duration.ofSeconds(1));

        this.client.post()
                .uri("/lec03/product")
                .body(mono, Product.class)
                .retrieve()
                .bodyToMono(Product.class)
                .doOnNext(System.out::println)
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }
}
