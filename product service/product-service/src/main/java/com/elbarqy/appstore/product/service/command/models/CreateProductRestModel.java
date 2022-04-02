package com.elbarqy.appstore.product.service.command.models;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class CreateProductRestModel {
    @NotBlank(message="title is a required field")
    private String title;

    @Min(value = 1,message = "Minimum value is 1")
    private BigDecimal price;
    @Min(value = 1,message = "Quantity can't be less than 1")
    @Max(value = 200, message = "Quantity can't be more than 200")
    private Integer quantity;
}
