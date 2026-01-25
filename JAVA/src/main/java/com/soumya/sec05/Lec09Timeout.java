package com.soumya.sec05;

import com.soumya.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec09Timeout {
    public static void main(String[] args) {

        getProductName()
                .timeout(Duration.ofSeconds(2))
                .onErrorReturn("fallback")
                .subscribe(Util.subscriber());


        Util.sleepSecond(5);

    }

    private static Mono<String> getProductName(){
        return Mono.fromSupplier(() -> Util.faker().commerce().productName())
                .delayElement(Duration.ofSeconds(2));
    }
}
