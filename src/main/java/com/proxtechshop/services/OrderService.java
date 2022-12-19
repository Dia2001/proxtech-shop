package com.proxtechshop.services;

import java.util.List;

import com.proxtechshop.api.request.PaymentCustomerRequest;
import com.proxtechshop.viewmodels.OrderViewModel;

public interface OrderService {
	boolean CreateOrder(PaymentCustomerRequest paymentCustomer);
	
	List<OrderViewModel> getOrdersByOneCustomer();
}
