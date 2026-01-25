package com.soumya.sec09;

import com.soumya.common.Util;
import com.soumya.sec09.helper.NameGenerator;

public class Lec02StartWithUsecase {

    public static void main(String[] args) {
        var nameGenerator = new NameGenerator();
        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("Sam"));

        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("Mike"));

        nameGenerator.generateNames()
                .take(3)
                .subscribe(Util.subscriber("Jake"));
    }
}
