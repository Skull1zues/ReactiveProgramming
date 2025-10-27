package com.soumya.Sec04;

import com.soumya.Sec04.helper.NameGenerator;
import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

public class Lec03FluxSinkTreadSafety {
    private static final Logger  log = LoggerFactory.getLogger(Lec03FluxSinkTreadSafety.class);

    public static void main(String[] args) {
        demo2();
    }

    private static void demo1(){
        var list = new ArrayList<>();
        Runnable runnable = ()-> {
            for(int i=0;i<1000;i++) {
                list.add(i);
            }
            };
            for(int i=0;i<10;i++){
                Thread.ofPlatform().start(runnable);
            }
            Util.sleepSecond(10);
            log.info("List size:- {}",list.size());
        }

        private static void demo2(){
            var list = new ArrayList<String>();
            var generator = new NameGenerator();
            var flux = Flux.create(generator);
            flux.subscribe(list::add);

            Runnable runnable = ()-> {
                for(int i=0;i<1000;i++) {
                    generator.generate();
                }
            };
            for(int i=0;i<10;i++){
                Thread.ofPlatform().start(runnable);
            }
            Util.sleepSecond(10);
            log.info("List size:- {}",list.size());



        }
    }

