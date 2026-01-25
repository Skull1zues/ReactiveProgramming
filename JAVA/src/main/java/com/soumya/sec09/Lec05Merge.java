package com.soumya.sec09;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05Merge {
    public static final Logger log = LoggerFactory.getLogger(Lec05Merge.class);

    public static void main(String[] args) {
        Flux.merge(producer1(),producer2(),producer4())
                .subscribe(Util.subscriber());

        Util.sleepSecond(4);

    }





    private static Flux<Integer> producer1(){
        return Flux.just(1,2,3)
                .transform(Util.fluxLogger("Producer1"))
                .doOnSubscribe(s -> log.info("Subscribe to producer1"))
                .delayElements(Duration.ofMillis(10));
    }
    private static Flux<Integer> producer2(){
        return Flux.just(10,20,30)
                .transform(Util.fluxLogger("Producer2"))
                .doOnSubscribe(s -> log.info("Subscribe to producer2"))
                .delayElements(Duration.ofMillis(10));
    }
    private static Flux<Integer> producer4(){
        return Flux.just(50,51,52)
                .transform(Util.fluxLogger("Producer4"))
                .doOnSubscribe(s -> log.info("Subscribe to producer4"))
                .delayElements(Duration.ofMillis(10));
    }
    private static Flux<Integer> producer3(){
        return Flux.error(new RuntimeException("Error"));
    }
}
