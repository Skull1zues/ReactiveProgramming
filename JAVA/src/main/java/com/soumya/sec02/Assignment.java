package com.soumya.sec02;

import com.soumya.common.Util;
import com.soumya.sec02.FileSystem.FileService;
import com.soumya.sec02.FileSystem.FileServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Assignment {
    private static final Logger logger = LoggerFactory.getLogger(Assignment.class);

    public static void main(String[] args) {

        var fileService = new FileServiceImpl();

        List<Mono> mono = new ArrayList<>();

        for(int i = 0; i<100;i++) {
            var write =fileService.write("file"+i+".txt", "TestFile"+i);
                    //.subscribe(Util.subscriber());

            var read = fileService.read("file"+i+".txt");
                    //.subscribe(Util.subscriber());
            var delete =fileService.delete("file"+i+".txt");
                    //.subscribe(Util.subscriber());
            mono.add(write);
            mono.add(read);
            mono.add(delete);

        }

        logger.info("Subcribing");
    }
}
