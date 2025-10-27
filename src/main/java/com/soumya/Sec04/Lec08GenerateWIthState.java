package com.soumya.Sec04;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;

public class Lec08GenerateWIthState {
    public static void main(String[] args) {

        Flux.generate(
                        () -> 0,
                        (counter , sink) -> {
                            var country = Util.faker().country().name();
                            sink.next(country);
                            counter++;
                            if(counter ==5)
                                sink.complete();
                            return counter;
                        }
                )
                .take(8)
                .subscribe(Util.subscriber());
    }
}
