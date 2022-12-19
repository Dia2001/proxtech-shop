package com.proxtechshop.converter;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Order;
import com.proxtechshop.entities.OrderDetail;
import com.proxtechshop.repositories.OrderDetailRepository;
import com.proxtechshop.repositories.ProductRepository;
import com.proxtechshop.viewmodels.OrderViewModel;

@Component
public class OrderConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private ProductRepository productRepository;
	
	public OrderViewModel converToModel(Order orderEntity) {

		OrderViewModel orderViewModel = modelMapper.map(orderEntity,OrderViewModel.class);
		List<OrderDetail> listOrderDetail=orderDetailRepository.findByOrderId(orderViewModel.getId());
		orderViewModel.setOrderDetails(null);
		return orderViewModel;
	}

}
