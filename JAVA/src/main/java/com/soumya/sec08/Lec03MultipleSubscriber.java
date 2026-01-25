package com.soumya.sec08;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03MultipleSubscriber {

    private static final Logger log = LoggerFactory.getLogger(Lec03MultipleSubscriber.class);

    public static void main(String[] args) {

        //System.setProperty("reactor.bufferSize.small", "16");


        var producer = Flux.generate(
                () -> 1,
                (state, sink) ->{
                    log.info("Generating state {}",state);
                    sink.next(state);
                    return ++state;
                }
        )
                .subscribeOn(Schedulers.parallel());

        producer
                .limitRate(2)
                .publishOn(Schedulers.boundedElastic())
                .map( i  ->timeConsumingTask((Integer) i))
                .subscribe(Util.subscriber("sub1"));

        producer
                .take(100)
                .publishOn(Schedulers.boundedElastic())
                //.map( i  ->timeConsumingTask((Integer) i))
                .subscribe(Util.subscriber("sub2"));



        Util.sleepSecond(60);
    }

    private static int timeConsumingTask(int i){
        log.info(String.valueOf(i));
        Util.sleepSecond(1);
        return i;
    }
}
