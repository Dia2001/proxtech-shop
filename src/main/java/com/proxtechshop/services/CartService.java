package com.proxtechshop.services;

import java.util.List;

import com.proxtechshop.api.request.CartRequest;
import com.proxtechshop.viewmodels.CartViewModel;

public interface CartService {
	
	boolean addProductToCart(CartRequest cartRequest);
	
	List<CartViewModel> getAllProductToCustomer();
}
