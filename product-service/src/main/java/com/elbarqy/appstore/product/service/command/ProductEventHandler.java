package com.elbarqy.appstore.product.service.command;

import com.elbarqy.appstore.core.events.ProductReservedEvent;
import com.elbarqy.appstore.product.service.core.data.models.ProductEntity;
import com.elbarqy.appstore.product.service.core.data.ProductRepository;
import com.elbarqy.appstore.product.service.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductEventHandler {
    private final ProductRepository productRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductEventHandler.class);

    @Autowired
    public ProductEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception exception) throws Exception {
        throw exception;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        try {
            productRepository.save(productEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void on(ProductReservedEvent event) {
        ProductEntity productEntity = productRepository.findByProductID(event.getProductID());
        LOGGER.info("+ ProductEventHandler " + productEntity.toString() + " " + event.getQuantity());
        productEntity.setQuantity(productEntity.getQuantity() - event.getQuantity());
        productRepository.save(productEntity);
    }
}
