package com.proxtechshop.api.restcontrollers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proxtechshop.api.request.CartRequest;
import com.proxtechshop.api.request.PaymentCustomerRequest;
import com.proxtechshop.api.request.ProductsPaymentRequest;
import com.proxtechshop.api.response.NotificationResponse;
import com.proxtechshop.api.response.ProductsPaymentResponse;
import com.proxtechshop.common.Constants;
import com.proxtechshop.services.CartService;
import com.proxtechshop.services.OrderService;
import com.proxtechshop.services.PaymentService;


@RestController
@RequestMapping("/api/v1/customer")
public class CustomerRestController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping(Constants.CART_ADDPRODUCT_URL_ACTION_API)
	public ResponseEntity<?> addProductToCart(@RequestBody CartRequest cartRequest) {

		boolean check = cartService.addProductToCart(cartRequest);
		
		NotificationResponse notification = new NotificationResponse();
		if (check) {
			notification.setMsg("Thêm sản phẩm vào giỏ hàng thành công");
		} else {
			notification.setMsg("Thêm sản phẩm vào giỏ hàng thất bại");
		}

		return new ResponseEntity<>(notification, HttpStatus.OK);
	}

	@PostMapping(Constants.PAYMENT_PRODUCTDETAIL_URL_API)
	public ResponseEntity<List<ProductsPaymentResponse>> productDetailpayment(@RequestBody ProductsPaymentRequest productPayment) {
		
		List<String> listProductId=productPayment.getListProductId();
		paymentService.listProductPayment(listProductId);
		
//        for (Iterator iterator = productPayment.getListProductId().iterator(); iterator.hasNext();) {
//			String type = (String) iterator.next();	
//		}
		return new ResponseEntity<>(paymentService.listProductPayment(listProductId), HttpStatus.OK);
	}
	
	@PostMapping(Constants.PAYMENT_URL_API)
	public ResponseEntity<?> payment(@RequestBody PaymentCustomerRequest paymentCustomer){
		
		boolean check=orderService.CreateOrder(paymentCustomer);
		
		NotificationResponse notification = new NotificationResponse();
		if (check) {
			notification.setMsg("Thêm sản phẩm vào giỏ hàng thành công");
		} else {
			notification.setMsg("Thêm sản phẩm vào giỏ hàng thất bại");
		}
		
		return new ResponseEntity<>(notification, HttpStatus.OK);
	}
	
	@PostMapping(Constants.ORDER_URL_API)
	public ResponseEntity<?> orderByOneCustomer(@RequestBody PaymentCustomerRequest paymentCustomer){
		
		boolean check=orderService.CreateOrder(paymentCustomer);
		
		NotificationResponse notification = new NotificationResponse();
		if (check) {
			notification.setMsg("Thêm sản phẩm vào giỏ hàng thành công");
		} else {
			notification.setMsg("Thêm sản phẩm vào giỏ hàng thất bại");
		}
		
		return new ResponseEntity<>(notification, HttpStatus.OK);
	}
}
