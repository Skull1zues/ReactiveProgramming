package com.soumya.sec09;

import com.soumya.common.Util;
import com.soumya.sec09.application.Order;
import com.soumya.sec09.application.OrderService;
import com.soumya.sec09.application.User;
import com.soumya.sec09.application.UserService;
import reactor.core.publisher.Flux;

public class Lec11FluxFlatMap {

    public static void main(String[] args) {


        UserService.getAllUser()
                .map(User::id)
                .flatMap(OrderService::getUserOrder,1)
                .subscribe(Util.subscriber());



        Util.sleepSecond(5);
    }
}
