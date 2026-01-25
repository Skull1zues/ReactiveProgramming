package com.soumya.sec03;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec09FluxInterval {

    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(500))
                .map(i-> Util.faker().address().city())
                .subscribe(Util.subscriber("Sub1"));



        Util.sleepSecond(10);
    }
}
