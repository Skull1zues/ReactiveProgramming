package com.soumya.sec05;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06ErrorHandler {
    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandler.class);

    public static void main(String[] args) {
        Mono.just(5)
                .map(i -> i==5? 5/0 :i) //intentional
                .onErrorResume(ex -> fallback())
                .subscribe(Util.subscriber());
    }

    private static void onErrorReturn(){
        Mono.just(5)
                .map(i -> i==5? 5/0 :i) //intentional
                //.onErrorReturn(-1)
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback(){
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(10,100));
    }
}
