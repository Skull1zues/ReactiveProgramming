package com.soumya.sec12;

import com.soumya.common.Util;
import reactor.core.publisher.Sinks;

public class Lec02SinkUnicast {

    public static void main(String[] args) {

        demo1();
    }

    private static void demo1(){
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        var flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("hello");
        sink.tryEmitNext("how are you");

        flux.subscribe(Util.subscriber("Sam"));
        flux.subscribe(Util.subscriber("Mike"));
    }
}
