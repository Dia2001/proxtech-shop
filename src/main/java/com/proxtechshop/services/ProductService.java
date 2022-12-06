package com.proxtechshop.services;

import com.proxtechshop.viewmodels.ProductDetailViewModel;

public interface ProductService {
	
	ProductDetailViewModel getOneProductDeTail(String productId);
	
}
