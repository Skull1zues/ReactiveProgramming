package com.soumya.sec11;

import com.soumya.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec01Repeat {
    public static void main(String[] args) {
        var mono = Mono.fromSupplier(() -> Util.faker().country().name());
        var mono1 = Mono.just(1);

        var subcriber = Util.subscriber();

        mono.subscribe(subcriber);
        mono1.repeat(10)
                .map(i -> i+1)
                .subscribe(subcriber);

        mono.repeat()
                .takeUntil(c -> c.equalsIgnoreCase("canada"))
                .subscribe(subcriber);

        mono.repeatWhen(flux -> flux.delayElements(Duration.ofSeconds(2)).take(2))
                .takeUntil(c -> c.equalsIgnoreCase("canada"))
                .subscribe(subcriber);


        Util.sleepSecond(10);


    }
}
