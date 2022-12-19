package com.proxtechshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Cart;

@Component
public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	public List<Cart> findByCustomer_id(String customerId);
	
	public Cart findByCustomer_idAndProduct_id(String customerId, String productId);
	
	public Cart findByProduct_id(String productId);
}
