package com.soumya.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec03MonoSubcribe {
    private static final Logger log = LoggerFactory.getLogger(Lec03MonoSubcribe.class);


    public static void main(String[] args) {
        var mono = Mono.just(1).map(ii -> ii/0);
        mono.subscribe(
                i -> log.info("received {}",i) ,
                err -> log.error("error",err),
                ()->log.info("Complete"),
                subscription -> subscription.request(1));
    }
}
