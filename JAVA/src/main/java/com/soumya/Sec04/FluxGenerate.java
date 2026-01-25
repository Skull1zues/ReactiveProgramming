package com.soumya.Sec04;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class FluxGenerate {
    public static void main(String[] args) {
        var i = new AtomicInteger(1);
        Flux.generate(synchronousSink -> {

            synchronousSink.next(i);
            i.getAndIncrement();
            if(i.get()==9){
                synchronousSink.complete();
            }
            // synchronousSink.next(2);
        })
                .take(8)
                .subscribe(Util.subscriber());
    }
}
