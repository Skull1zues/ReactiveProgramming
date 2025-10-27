package com.soumya.sec03;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec10FluxDefer {
    public static void main(String[] args) {
        var list = List.of(1,2,3,4,5,6);

        Flux.defer(()->Flux.fromIterable(list))
                .subscribe(Util.subscriber());
    }
}
