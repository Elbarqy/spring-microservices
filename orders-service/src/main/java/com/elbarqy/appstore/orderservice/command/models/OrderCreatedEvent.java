package com.elbarqy.appstore.orderservice.command.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderCreatedEvent {
    private String userID;
    public String orderID;
    private String productID;
    private Integer quantity;
    private String addressID;
    private OrderStatus orderStatus;
}
