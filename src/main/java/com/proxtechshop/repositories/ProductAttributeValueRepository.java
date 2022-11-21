package com.proxtechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.ProductAttributeValue;

@Component
public interface ProductAttributeValueRepository extends JpaRepository<ProductAttributeValue, Integer> {

}
