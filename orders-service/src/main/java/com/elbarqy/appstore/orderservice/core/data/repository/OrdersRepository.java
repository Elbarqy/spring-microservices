package com.elbarqy.appstore.orderservice.core.data.repository;

import com.elbarqy.appstore.orderservice.core.data.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity,String> {
}
