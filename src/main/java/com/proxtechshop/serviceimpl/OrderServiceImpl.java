package com.proxtechshop.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxtechshop.api.request.PaymentCustomerRequest;
import com.proxtechshop.common.Constants;
import com.proxtechshop.entities.Cart;
import com.proxtechshop.entities.Order;
import com.proxtechshop.entities.OrderDetail;
import com.proxtechshop.entities.OrderHistory;
import com.proxtechshop.entities.Product;
import com.proxtechshop.entities.User;
import com.proxtechshop.repositories.CartRepository;
import com.proxtechshop.repositories.OrderDetailRepository;
import com.proxtechshop.repositories.OrderHistoryRepository;
import com.proxtechshop.repositories.OrderRepository;
import com.proxtechshop.repositories.OrderStatusRepository;
import com.proxtechshop.repositories.ProductRepository;
import com.proxtechshop.repositories.UserRepository;
import com.proxtechshop.services.OrderService;
import com.proxtechshop.utils.GetUserUtil;
import com.proxtechshop.utils.Utils;
import com.proxtechshop.viewmodels.OrderViewModel;
import com.proxtechshop.converter.OrderConverter;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderStatusRepository orderStatusRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private OrderHistoryRepository orderHistoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderConverter orderConverter;
	
	@Override
	public boolean CreateOrder(PaymentCustomerRequest paymentCustomer) {
		
		Order orderCheck=null;
		
		OrderDetail orderDetailCheck=null;
		
		OrderHistory orderHistoryCheck=null;
		
		int totalPayment=0;
		
		User user = userRepository.getByUsername(GetUserUtil.GetUserName());
		
		Order order=new Order();
		order.setId(Utils.randomOrderId());
		order.setNameShip(user.getCustomer().getFullName());
		order.setAddressShip(user.getCustomer().getAddress());
		order.setPhoneShip(user.getCustomer().getPhone());
		order.setNote(paymentCustomer.getNote());
		order.setPromotion(Constants.PROMOTION);
		order.setCreatedDate(new Date());
		order.setOrderStatus(orderStatusRepository.findById("created").get());
		order.setCustomer(user.getCustomer());
		for(String productId : paymentCustomer.getListProductId()) {
			Cart cart=cartRepository.findByCustomer_idAndProduct_id(user.getCustomer().getId(),productId);
			System.out.println(cart.toString());
			totalPayment+=(Integer.parseInt(cart.getPrice().toString())*cart.getQuantity());
		}
		order.setCheckoutPrice(new BigDecimal(totalPayment));
		orderCheck=orderRepository.save(order);
		
		
		for(String productId : paymentCustomer.getListProductId()) {
			OrderDetail orderDetail=new OrderDetail();
			Cart cart=cartRepository.findByCustomer_idAndProduct_id(user.getCustomer().getId(),productId);
			Product product=cartRepository.findByCustomer_idAndProduct_id(user.getCustomer().getId(),productId).getProduct();
			orderDetail.setOrderId(order.getId());
			orderDetail.setProductId(productId);
			orderDetail.setQuantity(cart.getQuantity());
			orderDetailCheck= orderDetailRepository.save(orderDetail);
	
		}
		

		OrderHistory orderHistory=new OrderHistory();
		orderHistory.setCreatedDate(new Date());
		orderHistory.setOrder(order);
		orderHistory.setOrderStatus(order.getOrderStatus());
		orderHistoryCheck=orderHistoryRepository.save(orderHistory);
		
		// Delelet all product customer get payment out cart and the product is reduced in quatity
		
		for(String productId : paymentCustomer.getListProductId()) {
			Cart cart=cartRepository.findByCustomer_idAndProduct_id(user.getCustomer().getId(),productId);
			Product product=productRepository.findById(cart.getProductId()).get();
			product.setQuantity(product.getQuantity()-cart.getQuantity());
			productRepository.save(product);
			cartRepository.delete(cart);
		}
		
		return orderCheck != null && orderDetailCheck != null && orderHistoryCheck !=null ? true : false;
	}

	@Override
	public List<OrderViewModel> getOrdersByOneCustomer() {
		
		User user = userRepository.getByUsername(GetUserUtil.GetUserName());
		List<Order> orders=orderRepository.findByCustomer_id(user.getCustomer().getId()) ;
		List<OrderViewModel> orderVModels = new ArrayList<>();
		for (Order order : orders) {
			orderVModels.add(orderConverter.converToModel(order));
		}
		
		return null;
	}

}
