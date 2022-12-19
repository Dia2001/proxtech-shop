package com.proxtechshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxtechshop.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
	List<Brand> findTop3ByOrderByIdDesc();
	List<Brand> findByNameContains(String name);
	List<Brand> findByNameLike(String name);
}
