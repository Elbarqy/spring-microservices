package com.elbarqy.appstore.orderservice.command.rest;

import com.elbarqy.appstore.orderservice.command.models.CreateOrderCommand;
import com.elbarqy.appstore.orderservice.command.models.CreateOrderRest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {
    private final CommandGateway commandGateway;
    public OrderCommandController(CommandGateway comaCommandGateway){
        this.commandGateway = comaCommandGateway;
    }
    @PostMapping
    public String createOrder(@Valid @RequestBody CreateOrderRest createOrderRest){
        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .userID("27b95829-4f3f-4ddf-8983-151ba010e35b")
                .orderID(UUID.randomUUID().toString())
                .addressID(createOrderRest.getAddressID())
                .quantity(createOrderRest.getQuantity()).build();
        return commandGateway.sendAndWait(createOrderCommand);
    }
}
