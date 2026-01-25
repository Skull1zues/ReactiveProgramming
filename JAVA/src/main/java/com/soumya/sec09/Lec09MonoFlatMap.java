package com.soumya.sec09;

import com.soumya.common.Util;
import com.soumya.sec09.application.OrderService;
import com.soumya.sec09.application.PaymentService;
import com.soumya.sec09.application.UserService;
import reactor.core.publisher.Mono;

public class Lec09MonoFlatMap {

    public static void main(String[] args) {


        UserService.getUserId("sam")
                .flatMap(userId -> PaymentService.getUserBalance(userId))
                .subscribe(Util.subscriber());



        Util.sleepSecond(2);
    }
}
