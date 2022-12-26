package com.proxtechshop.api.restcontrollers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proxtechshop.api.request.CartRequest;
import com.proxtechshop.api.request.PaymentCustomerRequest;
import com.proxtechshop.api.request.ProductsPaymentRequest;
import com.proxtechshop.api.response.NotificationResponse;
import com.proxtechshop.api.response.OrderResponse;
import com.proxtechshop.api.response.ProductsPaymentResponse;
import com.proxtechshop.common.Constants;
import com.proxtechshop.converter.OrderConverter;
import com.proxtechshop.services.CartService;
import com.proxtechshop.services.OrderService;
import com.proxtechshop.services.PaymentService;
import com.proxtechshop.viewmodels.OrderViewModel;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerRestController {

	@Autowired
	CartService cartService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderConverter orderConverter;

	@PostMapping(Constants.CART_URL_ACTION)
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
	public ResponseEntity<List<ProductsPaymentResponse>> productDetailpayment(
			@RequestBody ProductsPaymentRequest productPayment) {

		System.out.println("aaaaaa"+productPayment.toString());
		List<String> listProductId = productPayment.getListProductId();
		paymentService.listProductPayment(listProductId,productPayment.getStatus());

//        for (Iterator iterator = productPayment.getListProductId().iterator(); iterator.hasNext();) {
//			String type = (String) iterator.next();	
//		}
		return new ResponseEntity<>(paymentService.listProductPayment(listProductId,productPayment.getStatus()), HttpStatus.OK);
	}

	@PostMapping(Constants.PAYMENT_URL_API)
	public ResponseEntity<?> payment(@RequestBody PaymentCustomerRequest paymentCustomer) {

		boolean check = orderService.CreateOrder(paymentCustomer);

		NotificationResponse notification = new NotificationResponse();
		if (check) {
			notification.setMsg("Thanh Toán thành công");
		} else {
			notification.setMsg("Thanh toán thất bại");
		}

		return new ResponseEntity<>(notification, HttpStatus.OK);
	}
	
	@PostMapping(Constants.PAYMENT_URL_REPURCHASE_API)
	public ResponseEntity<?> paymentRepurchase(@RequestBody PaymentCustomerRequest paymentCustomer) {

		boolean check = orderService.CreateRepruchase(paymentCustomer,paymentCustomer.getStatus());

		NotificationResponse notification = new NotificationResponse();
		if (check) {
			notification.setMsg("Thanh Toán thành công");
		} else {
			notification.setMsg("Thanh toán thất bại");
		}

		return new ResponseEntity<>(notification, HttpStatus.OK);
	}

	@PostMapping(Constants.ORDER_URL_API)
	public ResponseEntity<?> orderByOneCustomer(@RequestBody PaymentCustomerRequest paymentCustomer) {

		boolean check = orderService.CreateOrder(paymentCustomer);

		NotificationResponse notification = new NotificationResponse();
		if (check) {
			notification.setMsg("Tạo hóa đơn thành công");
		} else {
			notification.setMsg("Tạo hóa đơn thất bại");
		}

		return new ResponseEntity<>(notification, HttpStatus.OK);
	}

	@GetMapping(value =Constants.ORDER_URL_FILTER)
	public ResponseEntity<?> getFitterOrder(@RequestParam(value = "keyword", required =false) String keyword,@RequestParam(value = "statusId", required =false) String statusId) {
		
		try {
			System.out.println("nnnnenenenen");
			System.out.println("".equals(keyword)?true:false);
			System.out.println(keyword+"aaa"+"   "+statusId);
			if(keyword==null) {
				keyword="";
			}
			if(statusId==null) {
				statusId="";
			}
			List<OrderViewModel> orders = orderService.getOrdersByOneCustomer(keyword, statusId);
			System.out.println(orders.toString());
			List<OrderResponse> orderResponses=new ArrayList<>();
			for(OrderViewModel orderViewModel: orders) {
				orderResponses.add(orderConverter.converViewModel(orderViewModel));
			}
			return new ResponseEntity<>(orderResponses, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(Constants.ORDER_URL_REPURCHASE_API)
	public ResponseEntity<?> ifArrProduct(@RequestParam(value = "orderId", required = true) String orderId) {

		List<String> listProductId = orderService.getListIdProductOrder(orderId);
		return new ResponseEntity<>(listProductId, HttpStatus.OK);
	}
	
}
