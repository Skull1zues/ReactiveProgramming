package com.soumya._stWebFluxApplication.tests.sec07;

import com.soumya._stWebFluxApplication.tests.sec07.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

public class Lec01MonoTest extends AbstractWebClient{
    private final WebClient client = createWebClient();

    @Test
    public void simple_get() throws InterruptedException {
        this.client.get()
                .uri("/lec01/product/1")
                .retrieve()
                .bodyToMono(Product.class)
                .doOnNext(System.out::println)
                .subscribe();
        Thread.sleep(Duration.ofSeconds(2));
    }


    @Test
    public void concurrent_get() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            this.client.get()
                    .uri("/lec01/product/"+i)
                    .retrieve()
                    .bodyToMono(Product.class)
                    .doOnNext(System.out::println)
                    .subscribe();
        }
        Thread.sleep(Duration.ofSeconds(2));
    }
}
