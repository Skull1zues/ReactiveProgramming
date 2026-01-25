package com.soumya.sec02;

import com.soumya.common.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {
    public static void main(String[] args) {
        getUserName(4)
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getUserName(int useerId){
        return switch (useerId){
            case 1 -> Mono.just("Sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Invalid input"));
        };
    }
}
