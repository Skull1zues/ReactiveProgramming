package com.soumya.sec03;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxMultipleSubscriber {
    public static void main(String[] args) {
        var flux = Flux.just(1,2,3,4,5);
        flux.subscribe(Util.subscriber("Sub1"));
        flux.subscribe(Util.subscriber("Sub2"));
        flux.filter(i -> i%2==0).subscribe(Util.subscriber("sub3"));
    }
}
