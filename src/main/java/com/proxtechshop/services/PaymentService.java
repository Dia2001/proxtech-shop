package com.proxtechshop.services;

import java.util.List;

import com.proxtechshop.api.response.ProductsPaymentResponse;

public interface PaymentService extends InforCustomerService {
	
	List<ProductsPaymentResponse> listProductPayment(List<String> listProductId, String status);
	
}
