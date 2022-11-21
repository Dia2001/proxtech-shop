package com.proxtechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.ProductCategories;
import com.proxtechshop.utils.ProductCategoryId;

@Component
public interface ProductCategoryRepository extends JpaRepository<ProductCategories, ProductCategoryId> {

}
