package com.soumya.sec05;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;

public class Lec01Handle {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.range(1,10);
        flux.handle((item, sink) -> {
                    System.out.println(item);
            if(item == 5) {
                sink.error(new RuntimeException("oops"));
            }
        })
                .subscribe(Util.subscriber());


        Flux.range(1,100)
                .handle((item, sink) -> {
                    switch (item){
                        case 1 -> sink.next(-2);
                        case 4 -> {}
                        case 7 -> sink.error(new RuntimeException("oops"));
                        default -> sink.next(item);
                    }
                })
                .cast(Integer.class)
                .subscribe(Util.subscriber());

    }
}
