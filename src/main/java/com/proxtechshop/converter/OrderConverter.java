package com.proxtechshop.converter;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.loader.custom.Return;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proxtechshop.api.response.OrderResponse;
import com.proxtechshop.api.response.ProductOrderResponse;
import com.proxtechshop.entities.Order;
import com.proxtechshop.entities.OrderDetail;
import com.proxtechshop.entities.OrderStatus;
import com.proxtechshop.entities.ProductAttributeValue;
import com.proxtechshop.repositories.OrderDetailRepository;
import com.proxtechshop.repositories.ProductAttributeValueRepository;
import com.proxtechshop.repositories.ProductRepository;
import com.proxtechshop.viewmodels.OrderStatusViewModel;
import com.proxtechshop.viewmodels.OrderViewModel;
import com.proxtechshop.viewmodels.ProductOrder;

@Component
public class OrderConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductAttributeValueRepository productAttributeValueRepository;
	
	
	public OrderViewModel converToModel(Order orderEntity) {

		OrderViewModel orderViewModel = modelMapper.map(orderEntity,OrderViewModel.class);
		List<OrderDetail> listOrderDetail=new ArrayList<>() ;
		listOrderDetail=orderDetailRepository.findByOrderId(orderViewModel.getId());
		List<ProductOrder> listProduct=new ArrayList<>();
				
        for(OrderDetail orderDetail : listOrderDetail) {
        	ProductOrder productOrder=new ProductOrder();
        	productOrder.setProduct(productRepository.findById(orderDetail.getProductId()).get()); 
        	productOrder.setNumber(orderDetail.getQuantity());
        	ProductAttributeValue productAttributeValue=productAttributeValueRepository.findByProductIdAndAttributeId(orderDetail.getProductId(), 3);
        	if(productAttributeValue!=null) {
        		productOrder.setValue(productAttributeValue.getValue());
        	}
        	listProduct.add(productOrder);
        }
        orderViewModel.setProducts(listProduct);
		return orderViewModel;
	}

	public OrderResponse converViewModel(OrderViewModel orderViewModel) {
		OrderResponse ordRespon=new OrderResponse();
		List<ProductOrderResponse> productOrderResponses=new ArrayList<>();
		ordRespon.setId(orderViewModel.getId());
		ordRespon.setOrderStatusName(orderViewModel.getOrderStatus().getName());
		ordRespon.setCheckoutPrice(orderViewModel.getCheckoutPrice());
		for(ProductOrder productorder: orderViewModel.getProducts()) {
			ProductOrderResponse productOrderResponse=new ProductOrderResponse();
			productOrderResponse.setDescription(productorder.getProduct().getDescription());
			productOrderResponse.setName(productorder.getProduct().getName());
			productOrderResponse.setNumber(productorder.getNumber());
			productOrderResponse.setPrice(productorder.getProduct().getPrice());
			productOrderResponse.setThumbnail(productorder.getProduct().getThumbnail());
			productOrderResponses.add(productOrderResponse);
		}
		ordRespon.setProducts(productOrderResponses);
		return ordRespon;
	}
	
	public OrderStatusViewModel converModel(OrderStatus orderStatus) {
		OrderStatusViewModel orderStatusViewModel = modelMapper.map(orderStatus,OrderStatusViewModel.class);
		return orderStatusViewModel;
	}
}
