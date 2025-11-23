package com.soumya.sec11.client;

import com.soumya.common.AbstractHTTPClient;
import com.soumya.sec09.assesment.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClientResponse;

public class ExternalServiceClient extends AbstractHTTPClient {


    //public static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);



    public Mono<String> getProductName(int productId) {
        return get("/demo06/product/" + productId);
    }

    public Mono<String> getCountry() {
        return get("/demo06/country");
    }

    public Mono<String> get(String  path) {
        return this.httpClient.get()
                .uri(path)
                .response(this::toResponse)
                .next();
    }

    private Flux<String> toResponse(HttpClientResponse httpClientResponse, ByteBufFlux byteBufFlux){
        return switch (httpClientResponse.status().code()){
            case 200 -> byteBufFlux.asString();
            case 400 -> Flux.error(new ClientError());
            default -> Flux.error(new ServerError());
        };
    }

}
