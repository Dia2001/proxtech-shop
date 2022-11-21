package com.proxtechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Brand;

@Component
public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
