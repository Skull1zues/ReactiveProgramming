package com.soumya.sec12;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import java.time.Duration;
import java.util.Queue;

public class Lec05MulticastDirectBestEffort {

    private static final Logger log = LoggerFactory.getLogger(Lec05MulticastDirectBestEffort.class);


    public static void main(String[] args) {
        demo1();
    }

    private static void demo1(){

        System.setProperty("reactor.bufferSize.small","16");

        var sink = Sinks.many().multicast().onBackpressureBuffer();

        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Sam"));
        flux.subscribe(Util.subscriber("Jack"));
        flux.onBackpressureBuffer(100).delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("Mike"));


        for (int i = 0; i < 100; i++) {
            var res = sink.tryEmitNext(i);
            log.info("item: {}, Result: {}",i,res);

        }

        Util.sleepSecond(10);



    }


    private static void demo2(){

        System.setProperty("reactor.bufferSize.small","16");

        var sink = Sinks.many().multicast().directBestEffort();

        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Sam"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("Mike"));

        for (int i = 0; i < 100; i++) {
            var res = sink.tryEmitNext(i);
            log.info("item: {}, Result: {}",i,res);

        }

        Util.sleepSecond(10);



    }
}
