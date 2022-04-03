package com.elbarqy.appstore.product.service.command.interceptor;

import com.elbarqy.appstore.product.service.command.models.CreateProductCommand;
import com.elbarqy.appstore.product.service.core.data.ProductLookupRepository;
import com.elbarqy.appstore.product.service.core.data.models.ProductLookupEntity;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);
    private ProductLookupRepository productLookupRepository;

    @Autowired
    public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
            List<? extends CommandMessage<?>> list) {
        return (index, command) -> {
            LOGGER.info("INTERCEPTED A COMMAND of type" + command.getPayloadType() + " " + CreateProductCommand.class);
            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
                ProductLookupEntity productLookupEntity = productLookupRepository.findByProductIDOrTitle(
                        createProductCommand.getProductID(),
                        createProductCommand.getTitle());
                if(productLookupEntity != null){
                    throw new IllegalStateException("Product with productID or Title already exists");
                }

            }
            return (CommandMessage<?>) command;
        };
    }
}
