package com.proxtechshop.services;

import java.util.Map;

import com.proxtechshop.models.ProductFilter;
import com.proxtechshop.viewmodels.ProductDetailViewModel;
import com.proxtechshop.viewmodels.ProductPagingViewModel;

public interface ProductService {
	
	ProductDetailViewModel getOneProductDeTail(String productId);
	
	ProductPagingViewModel getFilter(ProductFilter filter, Map<Integer, String[]> attribute);
	
}
