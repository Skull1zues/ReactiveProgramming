package com.soumya.sec09;

import com.soumya.common.Util;
import com.soumya.sec09.application.OrderService;
import com.soumya.sec09.application.PaymentService;
import com.soumya.sec09.application.UserService;

public class Lec10MonoFlatMapMany {

    public static void main(String[] args) {


        UserService.getUserId("mike")
                .flatMapMany(userId -> OrderService.getUserOrder(userId))
                .subscribe(Util.subscriber());



        Util.sleepSecond(2);
    }
}
