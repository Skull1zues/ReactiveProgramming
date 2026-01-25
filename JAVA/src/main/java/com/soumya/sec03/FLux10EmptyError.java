package com.soumya.sec03;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;

public class FLux10EmptyError {

    public static void main(String[] args) {

        Flux.empty()
                .subscribe(Util.subscriber());

        Flux.error(new RuntimeException("Stopped"))
                .subscribe(Util.subscriber());
    }
}
