package com.soumya.sec10.groupByAssesment;

import com.soumya.common.Util;

public record PurchaseOrder(String item, String category, Integer price) {
    public static PurchaseOrder create(){
        return new PurchaseOrder(
                Util.faker().commerce().productName(),
                Util.faker().commerce().department(),
                Util.faker().random().nextInt(10,100)
        );
    }
}
