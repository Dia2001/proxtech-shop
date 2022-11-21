package com.proxtechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Order;

@Component
public interface OrderRepository extends JpaRepository<Order, String> {

}
