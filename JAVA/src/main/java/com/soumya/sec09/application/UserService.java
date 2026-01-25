package com.soumya.sec09.application;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class UserService {
    private static final Map<String, Integer> userTable = Map.of(
            "sam",1,
            "Mike",2,
            "Jake", 3
    );

    public static Flux<User> getAllUser(){
        return Flux.fromIterable(userTable.entrySet())
                .map(entry -> new User(entry.getValue(),entry.getKey()));
    }

    public static Mono<Integer> getUserId(String userName){
        return Mono.fromSupplier(() -> userTable.get(userName));
    }
}
