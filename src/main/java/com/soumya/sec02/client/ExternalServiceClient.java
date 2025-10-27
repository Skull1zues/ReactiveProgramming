package com.soumya.sec02.client;

import com.soumya.common.AbstractHTTPClient;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHTTPClient {
    public Mono<String> getProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo01/product/"+productId)
                .responseContent()
                .asString()
                .next();
    }
}
