package com.elbarqy.appstore.product.service.command;

import com.elbarqy.appstore.product.service.core.data.ProductLookupRepository;
import com.elbarqy.appstore.product.service.core.data.models.ProductLookupEntity;
import com.elbarqy.appstore.product.service.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventHandler {
    private final ProductLookupRepository productLookupRepository;

    @Autowired
    public ProductLookupEventHandler(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductLookupEntity productLookupEntity = new ProductLookupEntity(
                event.getProductID(),
                event.getTitle()
        );

        productLookupRepository.save(productLookupEntity);
    }
}
