package com.proxtechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Product;

@Component
public interface ProductRepository extends JpaRepository<Product, String> {

}
