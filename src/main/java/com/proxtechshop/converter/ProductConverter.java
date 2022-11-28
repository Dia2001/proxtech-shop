package com.proxtechshop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Product;
import com.proxtechshop.viewmodels.ProductDetailViewModel;

@Component
public class ProductConverter {

	@Autowired
	private ModelMapper modelMapper;
	/*
	 * public Type ojConvert; public Object ojTo;
	 * 
	 * public ProductConverter(Type ojConvert,Object ojTo) {
	 * this.ojConvert=ojConvert; this.ojTo=ojTo; }
	 * 
	 * public Object converTo() { // Can customize everything inside this.ojConvert=
	 * modelMapper.map(this.ojTo,this.ojConvert); return this.ojConvert; }
	 */

	public ProductDetailViewModel converToModel(Product productEntity) {
		// Can customize everything inside
		ProductDetailViewModel productDetailViewModel = modelMapper.map(productEntity, ProductDetailViewModel.class);
		return productDetailViewModel;
	}

}