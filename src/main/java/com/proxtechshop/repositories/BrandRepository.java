package com.proxtechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proxtechshop.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
