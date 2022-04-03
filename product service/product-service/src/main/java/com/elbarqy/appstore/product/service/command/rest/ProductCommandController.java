package com.elbarqy.appstore.product.service.command.rest;

import com.elbarqy.appstore.product.service.command.models.CreateProductCommand;
import com.elbarqy.appstore.product.service.command.models.CreateProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {
    private final Environment env;
    private final CommandGateway commandGateway;

    @Autowired
    public ProductCommandController(Environment env, CommandGateway commandGateway) {
        this.env = env;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductRestModel.getPrice())
                .title(createProductRestModel.getTitle())
                .quantity(createProductRestModel.getQuantity())
                .productID(UUID.randomUUID().toString()).build();
        String returnedValue = null;
        returnedValue = commandGateway.sendAndWait(createProductCommand);
//        try {
//            returnedValue = commandGateway.sendAndWait(createProductCommand);
//        } catch (Exception ex) {
//            returnedValue = ex.getLocalizedMessage();
//        }
        return returnedValue;
    }
}
