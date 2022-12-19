package com.proxtechshop.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxtechshop.api.response.ProductsPaymentResponse;
import com.proxtechshop.converter.ProductConverter;
import com.proxtechshop.converter.UserConverter;
import com.proxtechshop.repositories.CartRepository;
import com.proxtechshop.services.InforCustomerService;
import com.proxtechshop.services.PaymentService;
import com.proxtechshop.viewmodels.PaymentCustomerDetailViewModel;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	InforCustomerService parentService;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	private ProductConverter productConverter;
	
	@Override
	public List<ProductsPaymentResponse> listProductPayment(List<String> listProductId) {
		
		List<ProductsPaymentResponse> listProduct= new ArrayList<ProductsPaymentResponse>();
		for(String productId:listProductId) {
			ProductsPaymentResponse product=productConverter.converToModel(cartRepository.findByProduct_id(productId));
			System.out.println(product.toString());
			listProduct.add(product);
		}
		return listProduct;
	}

	@Override
	public PaymentCustomerDetailViewModel inforCustomerPayment() {
		
		return  parentService.inforCustomerPayment();
	}

}
