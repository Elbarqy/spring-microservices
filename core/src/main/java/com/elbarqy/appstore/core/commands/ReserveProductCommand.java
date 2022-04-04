package com.elbarqy.appstore.core.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ReserveProductCommand {
    @TargetAggregateIdentifier
    private final String productID;
    private final String userID;
    private final Integer quantity;
    private final String orderID;
}
