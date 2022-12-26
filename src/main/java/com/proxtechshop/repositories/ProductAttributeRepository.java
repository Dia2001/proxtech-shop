package com.proxtechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.ProductAttribute;

@Component
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Integer> {

	ProductAttribute getReferenceByName(String name);
}
