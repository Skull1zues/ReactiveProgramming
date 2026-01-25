package com.soumya.sec03;

import com.soumya.common.Util;
import com.soumya.sec03.helper.NameGenerator;

public class Lec07FluxVsList {

    public static void main(String[] args) {
        var list = NameGenerator.getNamesList(10);
        System.out.println(list);
        NameGenerator.getNameFlux(100)
                .subscribe(Util.subscriber());
    }
}
