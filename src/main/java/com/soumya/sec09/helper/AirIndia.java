package com.soumya.sec09.helper;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class AirIndia {

    private static final String AIRLINE = "AirIndia";

    public static Flux<Flight> getFlight(){
        return Flux.range(1, Util.faker().random().nextInt(2,10))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(200,1000)))
                .map(i -> new Flight(AIRLINE,Util.faker().random().nextInt(500,100000)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
