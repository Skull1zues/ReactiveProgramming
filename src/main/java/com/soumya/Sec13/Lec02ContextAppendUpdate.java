package com.soumya.Sec13;

import com.soumya.common.Util;
import org.slf4j.Logger;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec02ContextAppendUpdate {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec02ContextAppendUpdate.class);

    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(ctx ->{
            log.info("{}",ctx);

            if(ctx.hasKey("user")) {
                return Mono.just("Welcome " + ctx.get("user"));
            }else {
                return Mono.error(new RuntimeException("Not Authenticated"));
            }
        });
    }

    private static void append(){
        getWelcomeMessage()
                .contextWrite(Context.of("a","b").put("user1","john"))
                .contextWrite(Context.of("user","sam"))
                .subscribe(Util.subscriber());
    }

    public static void main(String[] args) {
        getWelcomeMessage()

                .contextWrite(ctx ->Context.empty())
                .contextWrite(Context.of("a","b").put("user1","john"))
                .contextWrite(Context.of("user","sam"))
                .subscribe(Util.subscriber());
    }
}
