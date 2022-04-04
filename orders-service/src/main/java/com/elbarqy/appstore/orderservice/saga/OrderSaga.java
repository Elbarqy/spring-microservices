package com.elbarqy.appstore.orderservice.saga;

import com.elbarqy.appstore.core.commands.ReserveProductCommand;
import com.elbarqy.appstore.core.events.ProductReservedEvent;
import com.elbarqy.appstore.orderservice.command.models.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.CommandGatewayFactory;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;

@Saga
public class OrderSaga {
    @Inject
    private transient CommandGateway commandGateway;
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderSaga.class);

    @StartSaga
    @SagaEventHandler(associationProperty = "orderID")
    public void handle(OrderCreatedEvent orderCreatedEvent) {
        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .orderID(orderCreatedEvent.getOrderID())
                .productID(orderCreatedEvent.getProductID())
                .userID(orderCreatedEvent.getUserID())
                .quantity(orderCreatedEvent.getQuantity()).build();
        LOGGER.info("+ SAGA OrderCreatedEvent was handled for orderID " + reserveProductCommand.getOrderID()
                + " and productID of " + reserveProductCommand.getProductID());
        try {
            commandGateway.sendAndWait(reserveProductCommand);
        } catch (Exception e) {
            LOGGER.error("- ORDER SAGA Error " + e.getMessage());
        }
    }

    @SagaEventHandler(associationProperty = "orderID")
    public void handler(ProductReservedEvent productReservedEvent) {
        //
        //process payment
        LOGGER.info("+ ProductReservedEvent is called for productID " + productReservedEvent.getProductID()
                + " and orderID of " + productReservedEvent.getOrderID());
    }
}
