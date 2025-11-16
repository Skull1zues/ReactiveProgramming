package com.soumya.sec09;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec07Zip {
    public static final Logger log = LoggerFactory.getLogger(Lec07Zip.class);

    record Car(String body, String engine, String tyres, Integer sheet){}
    public static void main(String[] args) {
        Flux.zip(getBody(),getEngine(),getTyres(),getSheet())
                .map(t -> new Car(t.getT1(),t.getT2(),t.getT3(),t.getT4()))
                        .subscribe(Util.subscriber());

        Util.sleepSecond(4);

    }

    private static Flux<String> getBody(){
        return Flux.range(1,5)
                .map(i -> "body__"+ i)
                .delayElements(Duration.ofMillis(100));
    }

    private static Flux<String> getEngine(){
        return Flux.range(1,3)
                .map(i -> "Engine__"+ i)
                .delayElements(Duration.ofMillis(500));
    }

    private static Flux<String> getTyres(){
        return Flux.range(1,10)
                .map(i -> "Tyres__"+ i)
                .delayElements(Duration.ofMillis(50));
    }

    private static Flux<Integer> getSheet(){

        return Flux.range(1,10)
                .delayElements(Duration.ofMillis(50));

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
