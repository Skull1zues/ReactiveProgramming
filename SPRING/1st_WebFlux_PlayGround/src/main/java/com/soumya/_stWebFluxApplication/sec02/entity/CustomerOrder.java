package com.soumya._stWebFluxApplication.sec02.entity;

import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.UUID;

public class CustomerOrder {

    @Id
    private Integer id;
    private Integer customerId;
    private Integer productId;
    private Integer amount;
    private Instant orderDate;

    public CustomerOrder() {
    }

    public CustomerOrder(Integer id, Integer customerId, Integer productId, Integer amount, Instant orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.amount = amount;
        this.orderDate = orderDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }
}
