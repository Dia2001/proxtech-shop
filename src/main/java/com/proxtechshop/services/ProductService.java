package com.proxtechshop.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;


import com.proxtechshop.entities.Product;
import com.proxtechshop.viewmodels.ProductDetailViewModel;

public interface ProductService {
	ProductDetailViewModel getOneProductDeTail(String productId);
	List<Product> getAllProduct();
	void DeleteProduct(String id);
	Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	Product getProductById(String id);
	boolean updateProduct(Product product);
	HashMap<String, String> showAtrsAndValues(String productId);
}
