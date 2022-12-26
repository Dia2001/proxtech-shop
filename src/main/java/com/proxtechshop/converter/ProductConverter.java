package com.proxtechshop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proxtechshop.api.response.ProductsPaymentResponse;
import com.proxtechshop.entities.Cart;
import com.proxtechshop.entities.Customer;
import com.proxtechshop.entities.OrderDetail;
import com.proxtechshop.entities.Product;
import com.proxtechshop.entities.User;
import com.proxtechshop.repositories.ProductRepository;
import com.proxtechshop.viewmodels.CustomUserModelView;
import com.proxtechshop.viewmodels.ProductDetailViewModel;
import com.proxtechshop.viewmodels.UserView;

@Component
public class ProductConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	ProductRepository productRepository;
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
		
		ProductDetailViewModel productDetailViewModel = modelMapper.map(productEntity, ProductDetailViewModel.class);
		return productDetailViewModel;
	}

	public ProductsPaymentResponse converToModel(Cart cartEntity) {
		ProductsPaymentResponse productsPaymentResponse = new ProductsPaymentResponse();
		productsPaymentResponse.setProduct_id(cartEntity.getProductId());
		productsPaymentResponse.setName(cartEntity.getProduct().getName());
		productsPaymentResponse.setDescription(cartEntity.getProduct().getDescription());
		productsPaymentResponse.setThumbnail(cartEntity.getProduct().getThumbnail());
		productsPaymentResponse.setPrice(cartEntity.getPrice());
		productsPaymentResponse.setQuantity(cartEntity.getQuantity());
		return productsPaymentResponse;
	}
	
	public ProductsPaymentResponse converOrderToModel(OrderDetail orderDetailEntity) {
		
		ProductsPaymentResponse productsPaymentResponse = new ProductsPaymentResponse();
		productsPaymentResponse.setProduct_id(orderDetailEntity.getProductId());
		Product product=productRepository.findById(orderDetailEntity.getProductId()).get();
		productsPaymentResponse.setName(product.getName());
		productsPaymentResponse.setDescription(product.getDescription());
		productsPaymentResponse.setThumbnail(product.getThumbnail());
		productsPaymentResponse.setPrice(product.getPrice());
		productsPaymentResponse.setQuantity(orderDetailEntity.getQuantity());
		return productsPaymentResponse;
		
	}
}
