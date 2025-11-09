package com.soumya.sec06;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04HotPublisherCache {
    public static final Logger log = LoggerFactory.getLogger(Lec04HotPublisherCache.class);

    public static void main(String[] args) {
        var stockFlux = stockStream().replay(1).autoConnect(0);

        //var movieFlux = movieStream().publish().refCount(2);

        Util.sleepSecond(4);

        log.info("Sam join after 4 second ");
        stockFlux
                .take(10)
                .subscribe(Util.subscriber("sam"));

        Util.sleepSecond(4);

        stockFlux
                .take(3)
                .subscribe(Util.subscriber("Ram"));

        Util.sleepSecond(30);
    }
    private static Flux<Integer> stockStream(){
        return Flux.generate(
                sink -> sink.next(Util.faker().random().nextInt(10,100))
                )
                .take(20)
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("Emitting Price: {}",price))
                .cast(Integer.class);
    }
}
