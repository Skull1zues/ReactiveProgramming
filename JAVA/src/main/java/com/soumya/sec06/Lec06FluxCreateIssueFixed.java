package com.soumya.sec06;

import com.soumya.Sec04.helper.NameGenerator;
import com.soumya.common.Util;
import reactor.core.publisher.Flux;

public class Lec06FluxCreateIssueFixed {
    public static void main(String[] args) {
        var generator = new NameGenerator();

        var flux = Flux.create(generator).share();
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));



        for(int i=0;i<100;i++){
            generator.generate();
        }


    }
}
