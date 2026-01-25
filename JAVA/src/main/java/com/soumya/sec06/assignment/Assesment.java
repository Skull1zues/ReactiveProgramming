package com.soumya.sec06.assignment;

import com.soumya.common.Util;

import java.lang.reflect.UndeclaredThrowableException;

public class Assesment {
    public static void main(String[] args) {
        var client =new ExternalServiceClient();
        var inventoryService = new InventoryService();
        var revenueService = new RevenueService();

        client.orderStream().subscribe(inventoryService::consume);
        client.orderStream().subscribe(revenueService::consume);

        inventoryService.stream()
                .subscribe(Util.subscriber("Inventory"));

        revenueService.stream()
                .subscribe(Util.subscriber("Revenue"));

        Util.sleepSecond(10);
    }
}
