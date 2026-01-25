package com.soumya.sec09.helper;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class Kayak {
    public static Flux<Flight> getFlight(){
        return Flux.merge(
                AirIndia.getFlight(),
                Akasha.getFlight(),
                Indigo.getFlight(),
                JetAirways.getFlight()
        )
                .take(Duration.ofSeconds(2));
    }
}
