package com.proxtechshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proxtechshop.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	List<Category> findTop3ByOrderByIdDesc();
	List<Category> findByNameLike(String name);
	List<Category> findByNameContains(String name);
}
