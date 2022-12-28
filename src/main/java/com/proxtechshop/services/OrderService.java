package com.proxtechshop.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.proxtechshop.api.request.PaymentCustomerRequest;
import com.proxtechshop.entities.Order;
import com.proxtechshop.entities.OrderStatus;
import com.proxtechshop.entities.User;
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
	
	//Paginate and filter
	public Page<OrderViewModel> paginated(int pageNo, int pageSize, String sortField, String sortDirection);
	public Page<OrderViewModel> FilterAndPaginated(String search,String status, int pageNo, int pageSize, String sortField,
			String sortDirection);
	
}
