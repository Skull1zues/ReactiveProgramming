package com.soumya.sec08;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Lec05BackPressureStrategies {

    private static final Logger log = LoggerFactory.getLogger(Lec05BackPressureStrategies.class);

    public static void main(String[] args) {

        //System.setProperty("reactor.bufferSize.small", "16");


        var producer = Flux.create(sink ->{

            for(int i=0; i<500 && !sink.isCancelled();i++){
                log.info("generating {}",i);
                sink.next(i);
                Util.sleep(Duration.ofMillis(50));
            }
            sink.complete();
        }
        )
                .subscribeOn(Schedulers.parallel());

        producer
                //.onBackpressureBuffer()
                //.onBackpressureError()
                //.onBackpressureBuffer(10)
                //.onBackpressureDrop()
                .onBackpressureLatest()
                .log()
                .limitRate(2)
                .publishOn(Schedulers.boundedElastic())
                .map( i  ->timeConsumingTask((Integer) i))
                .subscribe();





        Util.sleepSecond(60);
    }

    private static int timeConsumingTask(int i){
        log.info("Receive {}",i);
        Util.sleepSecond(1);
        return i;
    }
}
