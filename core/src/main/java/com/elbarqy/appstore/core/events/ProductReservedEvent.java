package com.elbarqy.appstore.core.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductReservedEvent {
    private final String productID;
    private final String userID;
    private final Integer quantity;
    private final String orderID;
}
