package com.soumya.Sec04;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;

public class Lec05TakeOperator {
    public static void main(String[] args) {
        Flux.range(1,10)
                .log("take")
                .map(i -> i*10+4)
                .take(3)
                .log("map")
                .map(i -> i-1)
                .log("sub")
                .subscribe(Util.subscriber());


        Flux.range(1,10)
                .log("takeWhile")
                .map(i -> i*10+1)
                .takeWhile( i -> i<50)
                .log("map")
                .map(i -> i-1)
                .log("sub")
                .subscribe(Util.subscriber());


        Flux.range(1,10)
                .log("takeUntil")
                .map(i -> i*10+1)
                .takeUntil( i -> i>50)
                .log("map")
                .map(i -> i-1)
                .log("sub")
                .subscribe(Util.subscriber());
    }
}
