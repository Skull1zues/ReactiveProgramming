package com.soumya.sec12.assesment;

import com.soumya.sec12.Lec03SinkThreadSafety;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class SlackMember {
    private static final Logger log = LoggerFactory.getLogger(SlackMember.class);

    private final String name;
    private Consumer<String> messageConsumer;

    public SlackMember(String name){
        this.name = name;
    }

    void setMessageConsumer(Consumer<String> messageConsumer){
        this.messageConsumer = messageConsumer;
    }

    public void says(String message){
        this.messageConsumer.accept(message);
    }

    void receives(String message){
        log.info(message);
    }


    public String getName() {
        return name;
    }
}
