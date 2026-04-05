package com.soumya._stWebFluxApplication.tests.sec07;

import com.soumya._stWebFluxApplication.tests.sec07.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

public class Lec06QueryParamTest extends AbstractWebClient{
    private final WebClient client = createWebClient();
    @Test
    public void getDefaultHeader() throws InterruptedException {
        var path = "/lec06/calculator";
        var query = "first={first}&second={second}&operation={operation}";

        this.client.get()
                .uri(uriBuilder -> uriBuilder.path(path).query(query).build(10,20,"+"))
                .retrieve()
                .bodyToMono(Product.class)
                .doOnNext(System.out::println)
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }
}
