package com.proxtechshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proxtechshop.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
	List<Brand> findByNameContains(String name);
	List<Brand> findByNameLike(String name);
	
}
