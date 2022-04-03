package com.elbarqy.appstore.product.service.command;

import com.elbarqy.appstore.core.commands.ReserveProductCommand;
import com.elbarqy.appstore.core.events.ProductReservedEvent;
import com.elbarqy.appstore.product.service.command.models.CreateProductCommand;
import com.elbarqy.appstore.product.service.core.events.ProductCreatedEvent;
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
public class ProductCommandHandler {
    //Simply an aggregate that holds the state of the command
    //Materialize an event object
    //Aggregate that event object to axon
    @AggregateIdentifier
    private String productID;
    private String title;
    private BigDecimal price;
    private Integer quantity;


    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCommandHandler.class);

    public ProductCommandHandler() {
    }

    @CommandHandler
    public ProductCommandHandler(CreateProductCommand createProductCommand) {
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent);
    }

    @CommandHandler
    public void handler(ReserveProductCommand reserveProductCommand) {
        LOGGER.info(this.productID + " " + this.quantity + " " + reserveProductCommand);
        if (this.quantity < reserveProductCommand.getQuantity()) {
            throw new IllegalArgumentException("Insufficient number of items in stock");
        }
        ProductReservedEvent productReservedEvent = ProductReservedEvent.builder()
                .productID(reserveProductCommand.getProductID())
                .userID(reserveProductCommand.getUserID())
                .orderID(reserveProductCommand.getOrderID())
                .quantity(reserveProductCommand.getQuantity()).build();
        AggregateLifecycle.apply(productReservedEvent);
    }


    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.productID = productCreatedEvent.getProductID();
        this.title = productCreatedEvent.getTitle();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();
    }

    @EventSourcingHandler
    public void on(ProductReservedEvent productReservedEvent) {
        this.quantity -= productReservedEvent.getQuantity();
    }
}
