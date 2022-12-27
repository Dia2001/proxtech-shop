package com.proxtechshop.services;

import java.util.List;

import com.proxtechshop.api.request.PaymentCustomerRequest;
import com.proxtechshop.entities.Order;
import com.proxtechshop.entities.OrderStatus;
import com.proxtechshop.viewmodels.OrderStatusViewModel;
import com.proxtechshop.viewmodels.OrderViewModel;

public interface OrderService {
	boolean CreateOrder(PaymentCustomerRequest paymentCustomer);
	
	List<OrderViewModel> getOrdersByOneCustomer(String keyword,String statusId);
	
	OrderViewModel getOneOrderByCustomer(String orderId);
	
	List<String> getListIdProductOrder(String orderId);
	
	boolean CreateRepruchase(PaymentCustomerRequest paymentCustomer, String orderId);
	
	List<OrderViewModel> loadAllOrders();
	
	List<OrderStatusViewModel> loadAddOrderStatus();
	
	boolean updateStatus(String orderId, String statusId);
}
