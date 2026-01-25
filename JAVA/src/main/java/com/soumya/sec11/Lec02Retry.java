package com.soumya.sec11;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec02Retry {

    public static Logger log = LoggerFactory.getLogger(Lec02Retry.class);

    public static void main(String[] args) {
        demo2();
        Util.sleepSecond(10);
    }


    private static void demo1(){
        getCountryName()
                .retry(2)
                .subscribe(Util.subscriber("SUB1"));
    }

    private static void demo2(){
        getCountryName()
                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(1)))
                .subscribe(Util.subscriber("SUB2"));
    }

    private static Mono<String> getCountryName(){
        var atomicInteger = new AtomicInteger(0);

        return Mono.fromSupplier(() -> {
            if(atomicInteger.incrementAndGet()<3){
                throw new RuntimeException("OOps");
            }
            return Util.faker().country().name();
        })
                .doOnError(err -> log.error("error:- {}",err.getMessage()))
                .doOnSubscribe(s -> log.info("subscribing :- {}",s));
    }
}
