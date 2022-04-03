package com.elbarqy.appstore.product.service.query.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRestModel {
    private String productID;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
