package com.elbarqy.appstore.orderservice.command.models;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class CreateOrderRest {
    @NotBlank
    private String productID;
    @Min(value = 1)
    private BigDecimal quantity;
    @NotBlank
    private String addressID;
}
