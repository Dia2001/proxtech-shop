package com.proxtechshop.services;

import java.util.List;

import com.proxtechshop.entities.Brand;


public interface BrandService {
	  boolean create(Brand brand);
	   boolean updateBrand(int id,String data);
	   List<Brand> loadAll();
	   List<Brand> searchBrand(String search);
}
