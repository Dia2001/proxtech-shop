package com.proxtechshop.repositories;

import java.util.List;

import com.proxtechshop.entities.Product;
import com.proxtechshop.models.ProductFilter;

public interface CustomProductRepository {

	List<Product> getFilter(int pageSize, ProductFilter filter);
	
	int getTotalRecordFilter(int pageSize, ProductFilter filter);
}
