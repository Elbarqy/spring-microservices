package com.elbarqy.appstore.core.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
@Data
@Builder
public class ReserveProductCommand {
    @TargetAggregateIdentifier
    private final String productID;
    private final String userID;
    private final BigDecimal quantity;
    private final String orderID;
}
