package com.soumya.sec03;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;

public class FluxLog {
    public static void main(String[] args) {

        Flux.range(1,1000)
                .log()
                .filter(i -> i%2==0)
                .log()
                .map(i->i*10)
                .log()
                .subscribe(Util.subscriber());
    }
}
