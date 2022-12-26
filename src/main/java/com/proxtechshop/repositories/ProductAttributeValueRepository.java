package com.proxtechshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.ProductAttributeValue;

@Component
public interface ProductAttributeValueRepository extends JpaRepository<ProductAttributeValue, Integer> {
	List<ProductAttributeValue> findAllByProductId(String id);
	
	ProductAttributeValue findByProductIdAndAttributeId(String productId, int attributeId);
}
