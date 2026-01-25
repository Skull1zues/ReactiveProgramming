package com.soumya.sec02;

import com.soumya.sec01.subcriber.SubcriberImplemetation;
import reactor.core.publisher.Mono;

public class Lec02MonoJust {
    public static void main(String[] args) {

        var mono=Mono.just("abc");
        var subscriber = new SubcriberImplemetation();
        mono.subscribe(subscriber);

        subscriber.getSubscription().request(10);
    }
}
