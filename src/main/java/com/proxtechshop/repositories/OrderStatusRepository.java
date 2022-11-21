package com.proxtechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.OrderStatus;

@Component
public interface OrderStatusRepository extends JpaRepository<OrderStatus, String> {

}