package com.soumya.Sec13;

import com.soumya.common.Util;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec01Context {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec01Context.class);

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
                .contextWrite(Context.of("user","sam"))
                .subscribe(Util.subscriber());
    }
}
