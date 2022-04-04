package com.elbarqy.appstore.orderservice.core.eventHandling;

import com.elbarqy.appstore.orderservice.command.models.OrderCreatedEvent;
import com.elbarqy.appstore.orderservice.core.data.entities.OrderEntity;
import com.elbarqy.appstore.orderservice.core.data.repository.OrdersRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("order-group")
public class OrderEventHandling {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderEventHandling.class);
    private final OrdersRepository orderRepository;

    @Autowired
    public OrderEventHandling(OrdersRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(event, orderEntity);
        LOGGER.info("+ Event Handled ");
        orderRepository.save(orderEntity);
    }
}
