package com.proxtechshop.services;

import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;

import com.proxtechshop.api.response.ProductStatisticsMonthResponse;
import com.proxtechshop.entities.Product;
import com.proxtechshop.viewmodels.ProductDetailViewModel;
import com.proxtechshop.viewmodels.ProductPagingViewModel;
import com.proxtechshop.models.ProductFilter;

public interface ProductService {
	
	ProductDetailViewModel getOneProductDeTail(String productId);
	
	ProductPagingViewModel getFilter(ProductFilter filter, Map<Integer, String[]> attribute);
	
	List<Product> getAllProduct();
	
	void DeleteProduct(String id);
	
	Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
	Product getProductById(String id);
	
	boolean updateProduct(Product product);
	
	HashMap<String, String> showAtrsAndValues(String productId);
	
	void setAttributeValues(Product product);

	List<ProductStatisticsMonthResponse> getProductPStatisticsDateStartDateEnd(Date start, Date end);
}
