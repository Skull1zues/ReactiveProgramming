package com.soumya.sec03.assesment;

import com.soumya.common.AbstractHTTPClient;
import reactor.core.publisher.Flux;

public class ExternalStockServiceClient extends AbstractHTTPClient {
    public Flux<Integer> getStock() {
        return this.httpClient.get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString()
                .map(Integer::parseInt);

    }
}
