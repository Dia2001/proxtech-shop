package com.proxtechshop.api.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proxtechshop.api.request.CartRequest;
import com.proxtechshop.api.response.CartResponse;
import com.proxtechshop.common.Constants;
import com.proxtechshop.services.CartService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerRestController {
	
	@Autowired
	CartService cartService;
	
	@PostMapping(Constants.CART_URL_ACTION)
	public ResponseEntity<?> addProductToCart(@RequestBody CartRequest cartRequest) {

		boolean check = cartService.addProductToCart(cartRequest);
		
		CartResponse result = new CartResponse();
		if (check) {
			result.setMsg("Thêm sản phẩm vào giỏ hàng thành công");
		} else {
			result.setMsg("Thêm sản phẩm vào giỏ hàng thất bại");
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
