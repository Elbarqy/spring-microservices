package com.elbarqy.appstore.orderservice.command.models;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class CreateOrderRest {
    @NotBlank
    public String productID;
    @Min(value = 1)
    public int quantity;
    @NotBlank
    public String addressID;
}
