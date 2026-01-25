package com.soumya.sec05;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec07DefaultByEmpty {

    public static void main(String[] args) {
        Mono.empty()
                .defaultIfEmpty("fallback")
                .subscribe(Util.subscriber());

        Flux.range(1,10)
                .filter( i -> i>11)
                .defaultIfEmpty(-1)
                .subscribe(Util.subscriber());
    }
}
