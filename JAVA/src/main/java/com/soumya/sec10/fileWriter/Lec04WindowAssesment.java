package com.soumya.sec10.fileWriter;

import com.soumya.common.Util;
import com.soumya.sec10.Lec03Window;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec04WindowAssesment {
    public static void main(String[] args) {

        var counter = new AtomicInteger(0);
        var fileNameFormat = "src/main/resources/sec10/file%d.txt";

        eventStream()
                .window(5)
                .flatMap(flux -> FileWriter.create(flux, Path.of(fileNameFormat.formatted(counter.incrementAndGet()))))

                .subscribe();

        Util.sleepSecond(50);
    }

    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(500))
                //.take(10)
                .map(i-> "event" +i);
    }
}
