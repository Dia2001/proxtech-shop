package com.proxtechshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Brand;

@Component
public interface BrandRepository extends JpaRepository<Brand, Integer> {
	List<Brand> findTop3ByOrderByIdDesc();
}
