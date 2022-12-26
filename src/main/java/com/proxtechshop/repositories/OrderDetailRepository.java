package com.proxtechshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.OrderDetail;
import com.proxtechshop.utils.OrderDetailId;

@Component
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
	
	List<OrderDetail> findByOrderId(String orderId);
	
	OrderDetail findByOrderIdAndProductId(String orderId,String productId);
}
