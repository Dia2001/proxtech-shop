package com.proxtechshop.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proxtechshop.api.request.PaymentCustomerRequest;
import com.proxtechshop.common.Constants;
import com.proxtechshop.entities.Cart;
import com.proxtechshop.entities.Order;
import com.proxtechshop.entities.OrderDetail;
import com.proxtechshop.entities.OrderHistory;
import com.proxtechshop.entities.OrderStatus;
import com.proxtechshop.entities.Product;
import com.proxtechshop.entities.ProductAttributeValue;
import com.proxtechshop.entities.User;
import com.proxtechshop.repositories.CartRepository;
import com.proxtechshop.repositories.OrderDetailRepository;
import com.proxtechshop.repositories.OrderHistoryRepository;
import com.proxtechshop.repositories.OrderRepository;
import com.proxtechshop.repositories.OrderStatusRepository;
import com.proxtechshop.repositories.ProductAttributeValueRepository;
import com.proxtechshop.repositories.ProductRepository;
import com.proxtechshop.repositories.UserRepository;
import com.proxtechshop.services.OrderService;
import com.proxtechshop.utils.GetUserUtil;
import com.proxtechshop.utils.Utils;
import com.proxtechshop.viewmodels.OrderStatusViewModel;
import com.proxtechshop.viewmodels.OrderViewModel;
import com.proxtechshop.viewmodels.ProductOrder;
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

	@Autowired
	private ProductAttributeValueRepository productAttributeValueRepository;


	@Override
	public boolean CreateOrder(PaymentCustomerRequest paymentCustomer) {

		Order orderCheck = null;

		OrderDetail orderDetailCheck = null;

		OrderHistory orderHistoryCheck = null;

		int totalPayment = 0;

		User user = userRepository.getByUsername(GetUserUtil.GetUserName());

		Order order = new Order();
		order.setId(Utils.randomOrderId());
		order.setNameShip(user.getCustomer().getFullName());
		order.setAddressShip(user.getCustomer().getAddress());
		order.setPhoneShip(user.getCustomer().getPhone());
		order.setNote(paymentCustomer.getNote());
		order.setPromotion(Constants.PROMOTION);
		order.setCreatedDate(new Date());
		order.setOrderStatus(orderStatusRepository.findById("created").get());
		order.setCustomer(user.getCustomer());
		for (String productId : paymentCustomer.getListProductId()) {
			Cart cart = cartRepository.findByCustomer_idAndProduct_id(user.getCustomer().getId(), productId);
			totalPayment += (Integer.parseInt(cart.getPrice().toString()) * cart.getQuantity());
		}
		order.setCheckoutPrice(new BigDecimal(totalPayment));
		orderCheck = orderRepository.save(order);

		for (String productId : paymentCustomer.getListProductId()) {
			OrderDetail orderDetail = new OrderDetail();
			Cart cart = cartRepository.findByCustomer_idAndProduct_id(user.getCustomer().getId(), productId);
			Product product = cartRepository.findByCustomer_idAndProduct_id(user.getCustomer().getId(), productId)
					.getProduct();
			orderDetail.setOrderId(order.getId());
			orderDetail.setProductId(productId);
			orderDetail.setQuantity(cart.getQuantity());
			orderDetailCheck = orderDetailRepository.save(orderDetail);

		}

		OrderHistory orderHistory = new OrderHistory();
		orderHistory.setCreatedDate(new Date());
		orderHistory.setOrder(order);
		orderHistory.setOrderStatus(order.getOrderStatus());
		orderHistoryCheck = orderHistoryRepository.save(orderHistory);

		// Delelet all product customer get payment out cart and the product is reduced
		// in quatity

		for (String productId : paymentCustomer.getListProductId()) {
			Cart cart = cartRepository.findByCustomer_idAndProduct_id(user.getCustomer().getId(), productId);
			Product product = productRepository.findById(cart.getProductId()).get();
			product.setQuantity(product.getQuantity() - cart.getQuantity());
			productRepository.save(product);
			cartRepository.delete(cart);
		}

		return orderCheck != null && orderDetailCheck != null && orderHistoryCheck != null ? true : false;
	}

	@Override
	public List<OrderViewModel> getOrdersByOneCustomer(String keyword, String statusId) {

		List<OrderViewModel> orderVModels = new ArrayList<>();
		User user = userRepository.getByUsername(GetUserUtil.GetUserName());
		List<Order> orders = orderRepository.findByCustomer_id(user.getCustomer().getId());

		if ("".equals(keyword) && "".equals(statusId)) {
			System.out.println("null");
			for (Order order : orders) {
				orderVModels.add(orderConverter.converToModel(order));
			}

		} else {

			if (!"".equals(keyword) && !"".equals(statusId) ){
				System.out.println("have");
				for (Order order : orders) {

					boolean check = false;

					List<OrderDetail> listOrderDetail = new ArrayList<>();
					listOrderDetail = orderDetailRepository.findByOrderId(order.getId());

					// check name product
					for (OrderDetail orderDetail : listOrderDetail) {

						Product product = productRepository.findById(orderDetail.getProductId()).get();
						if (product.getName().contains(keyword)) {
							check = true;
							break;
						}
					}
                    System.out.println(check);
					if (order.getOrderStatus().getKey().equalsIgnoreCase(statusId)
				|| order.getId().equalsIgnoreCase(keyword) && check==true) {
						orderVModels.add(orderConverter.converToModel(order));
					}
				}

			} else {
				if (!"".equals(keyword)) {
					System.out.println("keyword");
					for (Order order : orders) {
						boolean check = false;

						List<OrderDetail> listOrderDetail = new ArrayList<>();
						listOrderDetail = orderDetailRepository.findByOrderId(order.getId());

						// check name product
						for (OrderDetail orderDetail : listOrderDetail) {

							Product product = productRepository.findById(orderDetail.getProductId()).get();
							System.out.println(product.getName().toString());
							if (product.getName().contains(keyword)) {
								check = true;
								break;
							}
						}
 
						if (order.getId().equalsIgnoreCase(keyword) || check==true) {
							orderVModels.add(orderConverter.converToModel(order));
						}
					}

				} else {
					System.out.println("status");
					for (Order order : orders) {
						if (order.getOrderStatus().getKey().equalsIgnoreCase(statusId)) {
							orderVModels.add(orderConverter.converToModel(order));
						}
					}

				}

			}
		}
		return orderVModels;
	}

	@Override
	public OrderViewModel getOneOrderByCustomer(String orderId) {
		OrderViewModel orderViewmodel = orderConverter.converToModel(orderRepository.findById(orderId).get());
		return orderViewmodel;
	}

	@Override
	public List<String> getListIdProductOrder(String orderId) {
		
		List<String> listOrderId=new ArrayList<>();
		List<OrderDetail> listOrDet=orderDetailRepository.findByOrderId(orderId);	
		for(OrderDetail orderDetail: listOrDet) {
			listOrderId.add(orderDetail.getProductId());
		}
		
		return listOrderId;
	}

	@Override
	public boolean CreateRepruchase(PaymentCustomerRequest paymentCustomer, String orderId) {
		
		Order orderCheck = null;

		OrderDetail orderDetailCheck = null;

		OrderHistory orderHistoryCheck = null;

		int totalPayment = 0;

		User user = userRepository.getByUsername(GetUserUtil.GetUserName());

		Order order = new Order();
		order.setId(Utils.randomOrderId());
		order.setNameShip(user.getCustomer().getFullName());
		order.setAddressShip(user.getCustomer().getAddress());
		order.setPhoneShip(user.getCustomer().getPhone());
		order.setNote(paymentCustomer.getNote());
		order.setPromotion(Constants.PROMOTION);
		order.setCreatedDate(new Date());
		order.setOrderStatus(orderStatusRepository.findById("created").get());
		order.setCustomer(user.getCustomer());
		for (String productId : paymentCustomer.getListProductId()) {
			OrderDetail orderDetail =orderDetailRepository.findByOrderIdAndProductId(orderId, productId);
			System.out.println(orderDetail.toString());
			Product product=productRepository.findById(orderDetail.getProductId()).get();
			totalPayment += (Integer.parseInt(product.getPrice().toString()) * orderDetail.getQuantity());
		}
		order.setCheckoutPrice(new BigDecimal(totalPayment));
		orderCheck = orderRepository.save(order);

		for (String productId : paymentCustomer.getListProductId()) {
			OrderDetail orderDetail = new OrderDetail();
			OrderDetail orderDetailPayment =orderDetailRepository.findByOrderIdAndProductId(orderId, productId);
			orderDetail.setOrderId(order.getId());
			orderDetail.setProductId(productId);
			orderDetail.setQuantity(orderDetailPayment.getQuantity());
			orderDetailCheck = orderDetailRepository.save(orderDetail);

		}

		OrderHistory orderHistory = new OrderHistory();
		orderHistory.setCreatedDate(new Date());
		orderHistory.setOrder(order);
		orderHistory.setOrderStatus(order.getOrderStatus());
		orderHistoryCheck = orderHistoryRepository.save(orderHistory);

		// Delelet all product customer get payment out cart and the product is reduced
		// in quatity

		for (String productId : paymentCustomer.getListProductId()) {
			
			OrderDetail orderDetailPayment =orderDetailRepository.findByOrderIdAndProductId(orderId, productId);;
			Product product = productRepository.findById( orderDetailPayment.getProductId()).get();
			product.setQuantity(product.getQuantity() - orderDetailPayment.getQuantity());
			productRepository.save(product);
		}

		return orderCheck != null && orderDetailCheck != null && orderHistoryCheck != null ? true : false;
	}

	@Override
	public List<OrderViewModel> loadAllOrders() {

		List<OrderViewModel> orderVModels = new ArrayList<>();
		for(Order order: orderRepository.findAll()) {
			orderVModels.add(orderConverter.converToModel(order));
		}
		return orderVModels;
	}

	@Override
	public List<OrderStatusViewModel> loadAddOrderStatus() {
		
		List<OrderStatusViewModel> orderStatusViewModels = new ArrayList<>();
		for(OrderStatus orderS: orderStatusRepository.findAll()) {
			orderStatusViewModels.add(orderConverter.converModel(orderS));
		}
		
		return orderStatusViewModels;
	}

	@Override
	public boolean updateStatus(String orderId, String statusId) {
		Order check=null;
		OrderHistory orderHistoryCheck = null;
		
		Order order=orderRepository.findById(orderId).get();
		OrderStatus orderStatus=orderStatusRepository.findById(statusId).get();
		order.setOrderStatus(orderStatus);
		check=orderRepository.save(order);
		
		
		OrderHistory orderHistory = new OrderHistory();
		orderHistory.setCreatedDate(new Date());
		orderHistory.setOrder(order);
		orderHistory.setOrderStatus(order.getOrderStatus());
		orderHistoryCheck = orderHistoryRepository.save(orderHistory);
		
		// TODO Auto-generated method stub
		return check != null && orderHistory != null?true:false;
	}

	//pagination
	@Override
	public Page<OrderViewModel> paginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);	
		List<OrderViewModel> orders=loadAllOrders();
		
		@SuppressWarnings("unchecked")
		Page<OrderViewModel> convertToPage=(Page<OrderViewModel>) Utils.toPage(orders, pageable);
		return convertToPage;
	}

	@Override
	public Page<OrderViewModel> FilterAndPaginated(String search, String status, int pageNo, int pageSize,
			String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);	
		List<OrderViewModel> orderViewModels=new ArrayList<>();
		if(search=="") {
			orderViewModels=loadAllOrders();
		}else
		{
			orderViewModels.add(orderConverter.converToModel(orderRepository.getReferenceById(search)));
		}
		List<OrderViewModel> filter=findByStatus(orderViewModels, status);
		
		@SuppressWarnings("unchecked")
		Page<OrderViewModel> result = (Page<OrderViewModel>) Utils.toPage(filter, pageable);
		return result;
	}
	
	private List<OrderViewModel> findByStatus(List<OrderViewModel> list,String status){
		List<OrderViewModel> list2=new ArrayList<>();
		if(status=="")
		{
			return list;
		}
		else
		{
			for (OrderViewModel item : list) {
				if(item.getOrderStatus().getKey().equalsIgnoreCase(status))
					list2.add(item);
			}
			return list2;
		}
	}

	
}
