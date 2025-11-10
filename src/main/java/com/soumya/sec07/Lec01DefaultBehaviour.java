package com.soumya.sec07;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01DefaultBehaviour {
    public static final Logger log = LoggerFactory.getLogger(Lec01DefaultBehaviour.class);

    public static void main(String[] args) {
        var flux = Flux.create(sink ->{
            for(int i=1;i<3;i++){
                log.info("Generating {}",i);
                sink.next(i);
                Util.sleepSecond(1);
            }
            sink.complete();
        })

                .doOnNext( v -> log.info("value:- {}",v));

        Runnable runnable1 = () -> flux.subscribe(Util.subscriber("sub1"));
        Runnable runnable2 = () -> flux.subscribe(Util.subscriber("sub2"));

        Thread.ofPlatform().start(runnable1);
        Thread.ofPlatform().start(runnable2);

        //flux.subscribe(Util.subscriber("sub1"));
        //flux.subscribe(Util.subscriber("sub2"));



    }
}
