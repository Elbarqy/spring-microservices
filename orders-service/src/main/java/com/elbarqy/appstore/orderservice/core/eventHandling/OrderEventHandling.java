package com.elbarqy.appstore.orderservice.core.eventHandling;

import com.elbarqy.appstore.orderservice.command.models.OrderCreatedEvent;
import com.elbarqy.appstore.orderservice.core.data.entities.OrderEntity;
import com.elbarqy.appstore.orderservice.core.data.repository.OrdersRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandling {
    private final OrdersRepository orderRepository;
    public OrderEventHandling(OrdersRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    @EventHandler
    public void on(OrderCreatedEvent event){
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(event,orderEntity);
        orderRepository.save(orderEntity);
    }
}
