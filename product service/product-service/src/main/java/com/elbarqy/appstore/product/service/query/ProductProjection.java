package com.elbarqy.appstore.product.service.query;

import com.elbarqy.appstore.product.service.core.data.ProductEntity;
import com.elbarqy.appstore.product.service.core.data.ProductRepository;
import com.elbarqy.appstore.product.service.core.events.ProductCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductProjection {
    private final ProductRepository productRepository;

    @Autowired
    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        productRepository.save(productEntity);
    }
}
