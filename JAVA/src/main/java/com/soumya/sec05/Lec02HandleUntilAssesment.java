package com.soumya.sec05;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleUntilAssesment {
    public static void main(String[] args) {
        Flux.<String>generate(sink -> sink.next(Util.faker().country().name()))
                .handle((item,sink) -> {
                    if(item.equalsIgnoreCase("canada")){
                        sink.complete();
                    }else{
                        sink.next(item);
                    }
                })
                .subscribe(Util.subscriber());
    }
}
