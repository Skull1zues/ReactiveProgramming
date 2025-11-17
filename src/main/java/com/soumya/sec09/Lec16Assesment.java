package com.soumya.sec09;

import com.soumya.common.Util;
import com.soumya.sec09.application.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class Lec16Assesment {
    public static final Logger log = LoggerFactory.getLogger(Lec16Assesment.class);

    record UserInfo(Integer userId, String username, Integer balance, List<Order> orders){}
    public static void main(String[] args) {


        UserService.getAllUser()
                        .flatMap(Lec16Assesment::getUserInfo)
                                .subscribe(Util.subscriber());

        var flux = Flux.just("a","b","c");
        flux.startWith(flux)
                        .subscribe(Util.subscriber());

        Util.sleepSecond(10);



        Util.sleepSecond(30);

    }
    public static Mono<UserInfo> getUserInfo(User user){
        return Mono.zip(
                PaymentService.getUserBalance(user.id()),
                OrderService.getUserOrder(user.id()).collectList()
        )
                .map(t -> new UserInfo(user.id(), user.userName(), t.getT1(), t.getT2()));
    }


}
