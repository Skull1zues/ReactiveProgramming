package com.soumya._stWebFluxApplication.tests.sec09;

import com.soumya._stWebFluxApplication.sec09.dto.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

@AutoConfigureWebTestClient
@SpringBootTest(properties = "sec=sec09")
public class ServerSentEventTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void serverSentEventTest(){
        this.webTestClient.get()
                .uri("/products/stream/10000")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(ProductDto.class)
                .getResponseBody()
                .take(3)
                .doOnNext(System.out::println)
                .collectList()
                .as(StepVerifier::create)
                .assertNext(productDtos -> {
                    Assertions.assertNotNull(productDtos);
                    Assertions.assertTrue(productDtos.stream().allMatch(p -> p.price() <=10000));
                })
                .verifyComplete();
    }   
}
