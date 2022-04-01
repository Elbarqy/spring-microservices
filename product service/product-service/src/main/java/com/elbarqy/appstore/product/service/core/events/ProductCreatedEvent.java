package com.elbarqy.appstore.product.service.core.events;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreatedEvent {
    private String productID;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
