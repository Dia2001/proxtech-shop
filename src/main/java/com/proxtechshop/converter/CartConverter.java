package com.proxtechshop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Cart;
import com.proxtechshop.viewmodels.CartViewModel;

@Component
public class CartConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public CartViewModel converToModel(Cart cartEntity) {
			
			CartViewModel cartViewModel = modelMapper.map(cartEntity, CartViewModel.class);
			return cartViewModel;
	}
}
