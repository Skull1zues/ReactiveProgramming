package com.soumya.sec06.assignment;

import com.soumya.common.AbstractHTTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Objects;

public class ExternalServiceClient extends AbstractHTTPClient {

    public static final Logger log= LoggerFactory.getLogger(ExternalServiceClient.class);

    private Flux<Order> orderFlux;

    public Flux<Order> orderStream(){
        if(Objects.isNull(orderFlux)){
            this.orderFlux = getOrderStream();
        }

        return orderFlux;
    }


    private Flux<Order> getOrderStream() {
        return this.httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                .map(this::parse)
                .doOnNext( o -> log.info("{}",o))
                .publish()
                .refCount(2);
    }

    private Order parse(String message){
        var arr = message.split(":");
        return new Order(
                arr[1],
                Integer.parseInt(arr[2]),
                Integer.parseInt(arr[3])
        );
    }

}
