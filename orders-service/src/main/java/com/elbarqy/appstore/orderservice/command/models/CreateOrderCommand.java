package com.elbarqy.appstore.orderservice.command.models;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
public class CreateOrderCommand {
    private final String userID;
    @TargetAggregateIdentifier
    public final String orderID;
    private final String productID;
    private final Integer quantity;
    private final String addressID;
    private final OrderStatus orderStatus;
}
