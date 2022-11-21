package com.proxtechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Category;

@Component
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
