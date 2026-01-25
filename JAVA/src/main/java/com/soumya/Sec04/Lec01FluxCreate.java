package com.soumya.Sec04;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            fluxSink.next(1);
            fluxSink.next(2);
            fluxSink.complete();
        })
                .subscribe(Util.subscriber());


        Flux.create(fluxSink -> {
            var co =true;
                        while(co){
                            var country = Util.faker().country().name();
                            fluxSink.next(country);
                            if(country.equalsIgnoreCase("india")) {
                                fluxSink.complete();
                                co=false;
                            }
                        }
                    })
                    .subscribe(Util.subscriber());
    }
}
