package com.elbarqy.appstore.orderservice.saga;

import com.elbarqy.appstore.core.commands.ReserveProductCommand;
import com.elbarqy.appstore.orderservice.command.models.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class OrderSaga {
    private CommandGateway commandGateway;
    @Autowired
    public OrderSaga(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "orderID")
    public void handle(OrderCreatedEvent orderCreatedEvent){
        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .orderID(orderCreatedEvent.getOrderID())
                .productID(orderCreatedEvent.getProductID())
                .userID(orderCreatedEvent.getUserID())
                .quantity(orderCreatedEvent.getQuantity()).build();
        commandGateway.send(reserveProductCommand, (commandMessage, commandResultMessage) -> {
            if(commandResultMessage.isExceptional()){
                //Start compensating transaction
            }

        });
    }
}
