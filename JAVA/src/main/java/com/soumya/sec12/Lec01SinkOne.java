package com.soumya.sec12;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec01SinkOne {
    private static final Logger log = LoggerFactory.getLogger(Lec01SinkOne.class);

    public static void main(String[] args) {

        demo2();
    }
    private static void demo1(){
        var sink = Sinks.one();

        var mono = sink.asMono();

        mono.subscribe(Util.subscriber("Sub1"));

        //sink.tryEmitValue("hi");
        //sink.tryEmitEmpty();
        sink.tryEmitError(new RuntimeException("Oops"));
    }
    private static void demo2(){
        var sink = Sinks.one();

        var mono = sink.asMono();
        sink.tryEmitValue("hi");
        sink.tryEmitValue("Hello");
        mono.subscribe(Util.subscriber("Sub1"));
        mono.subscribe(Util.subscriber("Sub2"));

        //sink.tryEmitEmpty();
        //sink.tryEmitError(new RuntimeException("Oops"));
    }
    private static void demo3(){
        var sink = Sinks.one();
        var mono = sink.asMono();

        mono.subscribe(Util.subscriber("Sub2"));

        sink.emitValue("hi", (((signalType, emitResult) -> {
            log.info(signalType.name());
            log.info(emitResult.name());
            return false;
        })));

        sink.emitValue("hello", (((signalType, emitResult) -> {
            log.info(signalType.name());
            log.info(emitResult.name());
            return true;
        })));
    }

}
