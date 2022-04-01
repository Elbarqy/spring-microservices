package com.elbarqy.appstore.product.service.rest;

import com.elbarqy.appstore.product.service.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products") //http://localhost:8080/products
public class ProductController {
    private final Environment env;
    private final CommandGateway commandGateway;

    @Autowired
    public ProductController(Environment env, CommandGateway commandGateway) {
        this.env = env;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel createProductRestModel) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductRestModel.getPrice())
                .title(createProductRestModel.getTitle())
                .quantity(createProductRestModel.getQuantity())
                .productID(UUID.randomUUID().toString()).build();
        String returnedValue = null;
        try {
            returnedValue = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception ex) {
            returnedValue = ex.getLocalizedMessage();
        }
        return returnedValue;
    }

    @GetMapping
    public String getProduct() {
        return "HTTP GET handle String";
    }
}
