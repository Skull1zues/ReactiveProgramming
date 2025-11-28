package com.soumya.sec12;

import com.soumya.common.Util;
import reactor.core.publisher.Sinks;

public class Lec04Multicast {

    public static void main(String[] args) {
        demo2();
    }

    private static void demo1(){
        var sink = Sinks.many().multicast().onBackpressureBuffer();

        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Sam"));
        flux.subscribe(Util.subscriber("Mike"));


        sink.tryEmitNext("hi");
        sink.tryEmitNext("hello");
        sink.tryEmitNext("how are you");

        Util.sleepSecond(2);

        flux.subscribe(Util.subscriber("Jake"));
        sink.tryEmitNext("some mssg");


    }


    private static void demo2(){
        var sink = Sinks.many().multicast().onBackpressureBuffer();

        var flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("hello");
        sink.tryEmitNext("how are you");

        Util.sleepSecond(2);

        flux.subscribe(Util.subscriber("Jake"));
        flux.subscribe(Util.subscriber("Sam"));
        flux.subscribe(Util.subscriber("Mike"));


        sink.tryEmitNext("some mssg");


    }
}
