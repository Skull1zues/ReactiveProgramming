package com.soumya.Sec13.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter {

    private static final Map<String, Integer> categoryRateLimit = Collections.synchronizedMap(new HashMap<>());

    static {
        categoryRateLimit.put("GOLD", 5);
        categoryRateLimit.put("SILVER", 3);
        categoryRateLimit.put("BRONZE", 1);
    }

    static <T>Mono<T> limitCalls(){
        return Mono.deferContextual(ctx -> {
            var allowCall = ctx.<String>getOrEmpty("category")
                    .map(RateLimiter::canAllow)
                    .orElse(false);

            return allowCall ? Mono.empty() : Mono.error(new RuntimeException("Rate limit exceeded"));
        });
    }

    private static boolean canAllow(String category){
        var attempts = categoryRateLimit.getOrDefault(category, 0);
        if(attempts > 0){
                categoryRateLimit.put(category, attempts - 1);
                return true;
        }
        return false;
    }

    private static void refresh(){
        Flux.interval(Duration.ofSeconds(5))
                .startWith(0L)
                .subscribe(i -> {
                    categoryRateLimit.put("GOLD", 5);
                    categoryRateLimit.put("SILVER", 3);
                    categoryRateLimit.put("BRONZE", 1);
                });
    }

}
