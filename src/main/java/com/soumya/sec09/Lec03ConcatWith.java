package com.soumya.sec09;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

public class Lec03ConcatWith {
    public static final Logger log = LoggerFactory.getLogger(Lec03ConcatWith.class);

    public static void main(String[] args) {
        demo3();

        Util.sleepSecond(3);

    }

    private static void demo1() {
        producer1()
                .concatWithValues(-1, 0)
                .take(10)
                .subscribe(Util.subscriber());
    }


    private static void demo2(){
        producer1()
                .concatWith(producer2())
                .take(10)
                .subscribe(Util.subscriber());
    }

    private static void demo3(){
        Flux.concat(producer1(),producer2())
                .take(10)
                .subscribe(Util.subscriber());
    }
    private static void demo4(){
        producer1()

                .startWith(producer2())
                .startWith(0,1,2,3)
                .take(10)
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> producer1(){
        return Flux.just(1,2,3)
                .doOnSubscribe(s -> log.info("Subscribe to producer1"))
                .delayElements(Duration.ofMillis(10));
    }
    private static Flux<Integer> producer2(){
        return Flux.just(10,20,30)
                .doOnSubscribe(s -> log.info("Subscribe to producer2"))
                .delayElements(Duration.ofMillis(10));
    }
}
