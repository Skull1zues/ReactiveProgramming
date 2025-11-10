package com.soumya.sec07.client;

import com.soumya.common.AbstractHTTPClient;
import com.soumya.sec07.Lec05PublishOn;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ExternalServiceClient extends AbstractHTTPClient {


    public static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);
    public Mono<String> getProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo01/product/"+productId)
                .responseContent()
                .asString()
                .doOnNext( m -> log.info("next: {}",m))
                .next()
                .publishOn(Schedulers.boundedElastic());
    }
}
