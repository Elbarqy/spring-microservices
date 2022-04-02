package com.elbarqy.appstore.product.service.command;

import com.elbarqy.appstore.product.service.command.models.CreateProductCommand;
import com.elbarqy.appstore.product.service.core.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
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

    public ProductCommandHandler() {

    }

    @CommandHandler
    public ProductCommandHandler(CreateProductCommand createProductCommand) {
        //Validation
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.productID = productCreatedEvent.getProductID();
        this.title = productCreatedEvent.getTitle();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();
    }
}
