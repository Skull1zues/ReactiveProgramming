package com.soumya.Sec04.assesment;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;

import java.lang.reflect.UndeclaredThrowableException;
import java.nio.file.Path;

public class Assesment {
    public static void main(String[] args) {
        /*for(int i=0;i<1000;i++){
            System.out.println("line_no "+i);
        }*/
        var path = Path.of("src/main/resources/Filesystem/file.txt");
        var fileReaderService = new FileReaderServiceImpl();
        fileReaderService.read(path)
                .take(100)
                .takeUntil(s ->s.equalsIgnoreCase("line_no 50"))
                .subscribe(Util.subscriber());

        Flux.range(1,5)
                .takeUntil(i -> i<10)
                .subscribe(Util.subscriber());
    }

}
