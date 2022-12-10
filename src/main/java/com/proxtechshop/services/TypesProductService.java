package com.proxtechshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proxtechshop.entities.Brand;
import com.proxtechshop.entities.Category;


public interface TypesProductService {
	List<Brand> getAllBrand();
	List<Category> getAllCategory();
}
