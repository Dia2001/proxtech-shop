package com.proxtechshop.services;

import java.util.List;

import com.proxtechshop.entities.Brand;
import com.proxtechshop.entities.Category;


public interface TypesProductService {
	List<Brand> getAllBrand();
	List<Category> getAllCategory();
}
