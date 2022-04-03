package com.elbarqy.appstore.orderservice.core.data.entities;

import com.elbarqy.appstore.orderservice.command.models.OrderStatus;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @Column(unique = true)
    public String orderID;
    private String userID;
    private String productID;
    private int quantity;
    private String addressID;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
