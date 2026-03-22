package com.soumya._stWebFluxApplication.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@RestController
@RequestMapping("traditional")
public class TraditionalWebController {

    private static final Logger logger = LoggerFactory.getLogger((TraditionalWebController.class));
    private final RestClient restClient = RestClient.builder()
                                                    .baseUrl("http://localhost:7070")
                                                    .build();

    @GetMapping("products")
    public Mono<List<Product>> getProducts() {
        var list = Mono.fromCallable(() -> {
                    // 1. This is your existing blocking code
                    return restClient.get()
                            .uri("/demo01/products")
                            .retrieve()
                            .body(new ParameterizedTypeReference<List<Product>>() {});
                })
                // 2. This moves the execution off the Netty thread to a blocking-friendly thread
                .subscribeOn(Schedulers.boundedElastic());
        logger.info("getProducts {}", list);
        return list;
    }


}
