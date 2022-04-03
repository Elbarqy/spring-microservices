package com.elbarqy.appstore.orderservice.command.models;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
public class CreateOrderCommand {
    @TargetAggregateIdentifier
    private final String userID;
    public final String orderID;
    private final String productID;
    private final BigDecimal quantity;
    private final String addressID;
    private final OrderStatus orderStatus;
}
