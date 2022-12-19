package com.proxtechshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Order;

@Component
public interface OrderRepository extends JpaRepository<Order, String> {
	
	public List<Order> findByCustomer_id(String customerId);
}
