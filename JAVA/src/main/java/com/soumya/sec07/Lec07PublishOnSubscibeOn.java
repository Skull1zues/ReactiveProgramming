package com.soumya.sec07;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec07PublishOnSubscibeOn {
    public static final Logger log = LoggerFactory.getLogger(Lec07PublishOnSubscibeOn.class);

    public static void main(String[] args) {
        var flux = Flux.create(sink ->{
            for(int i=1;i<3;i++){
                log.info("Generating {}",i);
                sink.next(i);
                Util.sleepSecond(1);
            }
            sink.complete();
        })
                .publishOn(Schedulers.newParallel("puerperal1"))
                .doOnNext( v -> log.info("value:- {}",v))
                .doFirst(() ->log.info("First1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() ->log.info("First2"));


        Runnable runnable1 =() ->flux
                .subscribe(Util.subscriber("sub1"));

        //Runnable runnable2 =() ->flux.subscribe(Util.subscriber("sub2"));

        Thread.ofPlatform().start(runnable1);
        //Thread.ofPlatform().start(runnable2);

        Util.sleepSecond(10);
        //flux.subscribe(Util.subscriber("sub2"));



    }
}
