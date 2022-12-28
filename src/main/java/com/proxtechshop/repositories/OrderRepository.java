package com.proxtechshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Order;
import com.proxtechshop.viewmodels.OrderViewModel;

@Component
public interface OrderRepository extends JpaRepository<Order, String> {
	
	public List<Order> findByCustomer_id(String customerId);
	
	
	//@Query(value = "SELECT * FROM orders o WHERE o.customer_id= :customerId and o.id = :keyword",nativeQuery=true)
	//public List<Order> findByCustomerAndOrderId(@Param("customerId") String customerId,@Param("keyword") String keyword);
	
}
