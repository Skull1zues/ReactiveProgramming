package com.soumya.sec06;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Lec01ColdPublisher {
    public static final Logger log = LoggerFactory.getLogger(Lec01ColdPublisher.class);

    public static void main(String[] args) {

        AtomicInteger integer = new AtomicInteger(0);

        var flux = Flux.create(fluxSink -> {
            log.info("invoked");
            for( int i = 0; i<3;i++){
                fluxSink.next(integer.incrementAndGet());
            }
            fluxSink.complete();
        });
        flux.subscribe(Util.subscriber("Sub1"));
        flux.subscribe(Util.subscriber("Sub2"));

    }
}
