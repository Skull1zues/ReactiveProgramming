package com.soumya.sec10;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01Buffer {
    public static void main(String[] args) {
        demo3();
        Util.sleepSecond(60);

    }

    private static void demo1(){
        eventStream()
                .buffer()
                .subscribe(Util.subscriber());
    }

    private static void demo2(){
        eventStream()
                .buffer(3) // every 3 item
                .subscribe(Util.subscriber());
    }

    private static void demo3(){
        eventStream()
                .buffer(Duration.ofMillis(500)) // every 3 item
                .subscribe(Util.subscriber());
    }

    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(300))
                //.take(10)
                .map(i-> "event" +i);
    }
}
