package com.soumya.sec09;

import com.soumya.common.Util;
import com.soumya.sec09.helper.Kayak;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec06MergeUseCase {
    public static final Logger log = LoggerFactory.getLogger(Lec06MergeUseCase.class);

    public static void main(String[] args) {
        Kayak.getFlight()
                        .subscribe(Util.subscriber());

        Util.sleepSecond(4);

    }






}
