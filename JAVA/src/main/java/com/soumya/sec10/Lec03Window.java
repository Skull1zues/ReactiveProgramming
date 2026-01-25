package com.soumya.sec10;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec03Window {


    public static void main(String[] args) {
        eventStream()
                .window(5)
                .flatMap(Lec03Window::processEvents)
                .subscribe();

        Util.sleepSecond(50);
    }

    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(500))
                //.take(10)
                .map(i-> "event" +i);
    }

    private static Mono<Void> processEvents(Flux<String> flux){
        return flux.doOnNext(e -> System.out.print(e))
                .doOnComplete(System.out::println)
                .then();
    }
}
