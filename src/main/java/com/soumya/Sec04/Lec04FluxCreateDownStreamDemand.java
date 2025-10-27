package com.soumya.Sec04;

import com.soumya.common.Util;
import com.soumya.sec01.publisher.SubscriptionImpl;
import com.soumya.sec01.subcriber.SubcriberImplemetation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

public class Lec04FluxCreateDownStreamDemand {
    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreateDownStreamDemand.class);

    public static void main(String[] args) {
        productOnDemand();

        var subscriber = new SubcriberImplemetation();

        Flux.<String>create(fluxSink -> {
            for(int i = 0;i<10;i++){
                var name = Util.faker().name().firstName();
                log.info("Generated {}",name);
                fluxSink.next(name);
            }
            fluxSink.complete();
        })
                .subscribe( subscriber);

        //Util.sleepSecond(2);
        //subscriber.getSubscription().request(11);
    }

    private static void productOnDemand(){
        var subscriber = new SubcriberImplemetation();

        Flux.<String>create(fluxSink -> {

            fluxSink.onRequest(request ->{
                for(int i = 0;i<request && !fluxSink.isCancelled();i++){
                    var name = Util.faker().name().firstName();
                    log.info("Generated {}",name);
                    fluxSink.next(name);
                }
            });
                })
                .subscribe( subscriber);

        Util.sleepSecond(2);
        subscriber.getSubscription().request(5);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(5);

    }

}
