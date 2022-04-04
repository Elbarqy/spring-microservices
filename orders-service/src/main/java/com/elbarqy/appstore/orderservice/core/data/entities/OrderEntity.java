package com.elbarqy.appstore.orderservice.core.data.entities;

import com.elbarqy.appstore.orderservice.command.models.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {
    @Id
    @Column(unique = true)
    private String orderID;
    private String userID;
    private String productID;
    private Integer quantity;
    private String addressID;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
