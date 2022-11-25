package com.proxtechshop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Product;
import com.proxtechshop.entities.User;

@Component
public interface ProductRepository extends JpaRepository<Product, String> {
	/* Optional<Product> findById(String id); */
}
