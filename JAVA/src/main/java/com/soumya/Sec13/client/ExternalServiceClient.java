package com.soumya.Sec13.client;

import com.soumya.common.AbstractHTTPClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClientResponse;

public class ExternalServiceClient extends AbstractHTTPClient {


    //public static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);

    public Mono<String> getBook(){
        return this.httpClient.get()
                .uri("/demo07/book")
                .responseContent()
                .asString()
                .startWith(RateLimiter.limitCalls())
                .contextWrite(UserService.userCategoryContext())
                .next();
    }

}
