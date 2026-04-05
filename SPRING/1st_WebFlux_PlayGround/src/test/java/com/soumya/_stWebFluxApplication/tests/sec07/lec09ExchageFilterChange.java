package com.soumya._stWebFluxApplication.tests.sec07;

import com.soumya._stWebFluxApplication.tests.sec07.dto.Product;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.util.UUID;

public class lec09ExchageFilterChange
        extends AbstractWebClient{
    private static final Logger log = LoggerFactory.getLogger(lec09ExchageFilterChange.class);
    private final WebClient client = createWebClient
            (b ->b.filter(tokenGeneration()).filter(logUrl()));
    @Test
    public void bearerAuth() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            this.client.get()
                    .uri("/lec09/product/{id}", i)
                    .attribute("enabled-logging",i%2==0)
                    .retrieve()
                    .bodyToMono(Product.class)
                    .doOnNext(System.out::println)
                    .then()
                    .as(StepVerifier::create)
                    .expectComplete()
                    .verify();
        }
    }

    private ExchangeFilterFunction tokenGeneration(){
        return ((request, next) -> {
            var token = UUID.randomUUID().toString().replace("-", "");
            log.info("token: {}", token);
            var modifiedRequest= ClientRequest.from(request).headers(headers->headers.setBearerAuth(token)).build();
            return  next.exchange(modifiedRequest);
        });
    }
    private ExchangeFilterFunction logUrl(){
        return ((request, next) -> {
            var isEnabled = (Boolean) request.attributes().getOrDefault("enabled-logging",false);
            if (isEnabled) {
                log.info("URL: {}", request.url());
                log.info("Http Method: {}", request.method());
            }
            return next.exchange(request);
        });
    }

}
