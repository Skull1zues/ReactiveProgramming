package com.soumya.sec03;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec11FluxMono {
    public static void main(String[] args) {
        var flux = Flux.range(1,10);

            flux.next()
                    .subscribe(Util.subscriber());

    }

    private static void monoToFlux(){
        var mono = getUserName(1);
        save(Flux.from(mono));
    }

    private static Mono<String> getUserName(int usrId){
        return switch (usrId){
            case 1 -> Mono.just("ram");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Invalid"));

        };
    }

    private static void save(Flux<String> flux){
        flux.subscribe(Util.subscriber());
    }
}
