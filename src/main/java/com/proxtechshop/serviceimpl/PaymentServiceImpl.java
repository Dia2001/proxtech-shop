package com.proxtechshop.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxtechshop.api.response.ProductsPaymentResponse;
import com.proxtechshop.converter.ProductConverter;
import com.proxtechshop.converter.UserConverter;
import com.proxtechshop.entities.OrderDetail;
import com.proxtechshop.repositories.CartRepository;
import com.proxtechshop.repositories.OrderDetailRepository;
import com.proxtechshop.repositories.ProductRepository;
import com.proxtechshop.services.InforCustomerService;
import com.proxtechshop.services.PaymentService;
import com.proxtechshop.viewmodels.PaymentCustomerDetailViewModel;
import com.proxtechshop.viewmodels.ProductDetailViewModel;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	InforCustomerService parentService;
	
	@Autowired
	CartRepository cartRepository;
	
	
	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private ProductConverter productConverter;
	
	@Override
	public List<ProductsPaymentResponse> listProductPayment(List<String> listProductId, String status) {
		
		List<ProductsPaymentResponse> listProduct= new ArrayList<ProductsPaymentResponse>();
		if(status.equals("cart")) {
			for(String productId:listProductId) {
				ProductsPaymentResponse product=productConverter.converToModel(cartRepository.findByProduct_id(productId));
				System.out.println(product.toString());
				listProduct.add(product);
			}
		}else {
			for(String productId:listProductId) {
				OrderDetail orderDetail=orderDetailRepository.findByOrderIdAndProductId(status,productId);
				ProductsPaymentResponse product=productConverter.converOrderToModel(orderDetail);
				System.out.println(product.toString());
				listProduct.add(product);
			}
		}
		return listProduct;
	}

	@Override
	public PaymentCustomerDetailViewModel inforCustomerPayment() {
		
		return  parentService.inforCustomerPayment();
	}

}
