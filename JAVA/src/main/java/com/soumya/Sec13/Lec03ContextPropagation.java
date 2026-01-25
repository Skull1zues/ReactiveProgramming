package com.soumya.Sec13;

import com.soumya.common.Util;
import org.slf4j.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

public class Lec03ContextPropagation {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec03ContextPropagation.class);

    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(ctx ->{

            if(ctx.hasKey("user")) {
                return Mono.just("Welcome " + ctx.get("user"));
            }else {
                return Mono.error(new RuntimeException("Not Authenticated"));
            }
        });
    }

    public static void main(String[] args) {
        getWelcomeMessage()
                .concatWith(Flux.merge(producer1(),producer2().contextWrite(ctx ->Context.empty())))
                .contextWrite(Context.of("user","sam"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> producer1(){
        return Mono.<String>deferContextual(ctx ->{
            log.info("Producer1 Context: {}",ctx);
            return Mono.empty();
        })
                .subscribeOn(Schedulers.boundedElastic());
    }

    private static Mono<String> producer2(){
        return Mono.<String>deferContextual(ctx ->{
                    log.info("Producer2 Context: {}",ctx);
                    return Mono.empty();
                })
                .subscribeOn(Schedulers.boundedElastic());
    }
}
