package com.soumya.sec12;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lec05MulticastDirectAllOrNothing {

    private static final Logger log = LoggerFactory.getLogger(Lec05MulticastDirectAllOrNothing.class);


    public static void main(String[] args) {
        demo1();
    }

    private static void demo1(){

        System.setProperty("reactor.bufferSize.small","16");

        var sink = Sinks.many().multicast().directAllOrNothing();

        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Sam"));
        flux.subscribe(Util.subscriber("Jack"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("Mike"));


        for (int i = 0; i < 100; i++) {
            var res = sink.tryEmitNext(i);
            log.info("item: {}, Result: {}",i,res);

        }

        Util.sleepSecond(10);



    }

}
