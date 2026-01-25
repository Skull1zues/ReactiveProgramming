package com.soumya.sec09.application;

public record Order(Integer userId,
                    String productName,
                    Integer price) {
}
