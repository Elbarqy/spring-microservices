package com.elbarqy.appstore.orderservice.command.models;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateOrderCommand {
    @TargetAggregateIdentifier
    private final String userID;
    public final String orderID;
    private final String productID;
    private final int quantity;
    private final String addressID;
    private final OrderStatus orderStatus;
}
