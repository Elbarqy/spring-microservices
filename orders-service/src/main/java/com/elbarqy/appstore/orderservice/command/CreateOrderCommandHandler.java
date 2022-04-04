package com.elbarqy.appstore.orderservice.command;

import com.elbarqy.appstore.orderservice.command.models.CreateOrderCommand;
import com.elbarqy.appstore.orderservice.command.models.OrderCreatedEvent;
import com.elbarqy.appstore.orderservice.command.models.OrderStatus;
import com.elbarqy.appstore.orderservice.core.eventHandling.OrderEventHandling;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class CreateOrderCommandHandler {
    private String userID;
    @AggregateIdentifier
    private String orderId;
    private String productId;
    private Integer quantity;
    private String addressId;
    private OrderStatus orderStatus;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderEventHandling.class);

    public CreateOrderCommandHandler() {
    }

    @CommandHandler
    public CreateOrderCommandHandler(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand, orderCreatedEvent);
        LOGGER.info("+ Command Handler" + orderCreatedEvent.toString());
        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        LOGGER.info("+ OrderCreatedEvent Has been event sourced");
        this.userID = event.getUserID();
        this.productId = event.getProductID();
        this.orderId = event.getOrderID();
        this.orderStatus = event.getOrderStatus();
        this.quantity = event.getQuantity();
        this.addressId = event.getAddressID();
    }

}
