package com.proxtechshop.repositories;

import java.util.List;
import java.util.Map;

import com.proxtechshop.entities.Product;
import com.proxtechshop.models.ProductFilter;

public interface CustomProductRepository {

	List<Product> getFilter(int pageSize, ProductFilter filter, Map<Integer, String[]> attribute);
	
	int getTotalRecordFilter(int pageSize, ProductFilter filter, Map<Integer, String[]> attribute);
}
