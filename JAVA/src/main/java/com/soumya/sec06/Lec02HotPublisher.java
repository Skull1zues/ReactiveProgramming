package com.soumya.sec06;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec02HotPublisher {
    public static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

    public static void main(String[] args) {
        //var movieFlux = movieStream().share();

        var movieFlux = movieStream().publish().refCount(2);

        Util.sleepSecond(2);

        movieFlux
                .take(10)
                .subscribe(Util.subscriber("sam"));

        Util.sleepSecond(3);

        movieFlux
                .take(3)
                .subscribe(Util.subscriber("Ram"));

        Util.sleepSecond(30);
    }
    private static Flux<String> movieStream(){
        return Flux.generate(
                () -> {
                    log.info("Received a request");
                    return 1;
                },
                (state, sink) ->{
                    var scene = "movie scene "+ state;
                    log.info("playing {}",scene);
                    sink.next(scene);
                    return ++state;
                }
        )
                .take(20)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}
