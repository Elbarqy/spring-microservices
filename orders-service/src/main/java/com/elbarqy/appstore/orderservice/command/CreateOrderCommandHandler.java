package com.elbarqy.appstore.orderservice.command;

import com.elbarqy.appstore.orderservice.command.models.CreateOrderCommand;
import com.elbarqy.appstore.orderservice.command.models.OrderCreatedEvent;
import com.elbarqy.appstore.orderservice.command.models.OrderStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class CreateOrderCommandHandler {
    @AggregateIdentifier
    private String userId;
    private String orderId;
    private String productId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;

    public CreateOrderCommandHandler(){}

    @CommandHandler
    public CreateOrderCommandHandler(CreateOrderCommand createOrderCommand){
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand,orderCreatedEvent);
        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent){
        this.userId = orderCreatedEvent.getUserID();
        this.productId = orderCreatedEvent.getProductID();
        this.orderId = orderCreatedEvent.getOrderID();
        this.orderStatus = orderCreatedEvent.getOrderStatus();
        this.quantity = orderCreatedEvent.getQuantity();
        this.addressId = orderCreatedEvent.getAddressID();
    }

}
