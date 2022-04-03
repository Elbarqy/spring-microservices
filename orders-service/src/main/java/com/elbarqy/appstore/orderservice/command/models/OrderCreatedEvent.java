package com.elbarqy.appstore.orderservice.command.models;

import lombok.Data;

@Data
public class OrderCreatedEvent {
    private String userID;
    public String orderID;
    private String productID;
    private int quantity;
    private String addressID;
    private OrderStatus orderStatus;
}
