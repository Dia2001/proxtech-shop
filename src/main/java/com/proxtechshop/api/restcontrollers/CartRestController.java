package com.proxtechshop.api.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proxtechshop.api.request.IRCartRequest;
import com.proxtechshop.api.response.NotificationResponse;
import com.proxtechshop.common.Constants;
import com.proxtechshop.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
public class CartRestController {
	@Autowired
	CartService cartService; 
	
	@PostMapping(Constants.CART_URL_IRPODUCT)
	public ResponseEntity<?> irProduct(@RequestBody IRCartRequest ircartRequest) {

		boolean check = cartService.irProduct(ircartRequest.getIdProduct(),ircartRequest.getNumber());
		NotificationResponse notification = new NotificationResponse();
		if(ircartRequest.getNumber()==-1) {
			if (check) {
				notification.setMsg("Giảm sản phẩm thành công");
				notification.setCheck(true);
			} else {
				notification.setMsg("Giảm sản phẩm thất bại");
				notification.setCheck(false);
			}
		}else {
			if (check) {
				notification.setMsg("Tăng sản phẩm thành công");
				notification.setCheck(true);
			} else {
				notification.setMsg("Tăng sản phẩm thất bại");
				notification.setCheck(false);
			}
		}

		return new ResponseEntity<>(notification, HttpStatus.OK);
	}
	
	@PostMapping(Constants.CART_URL_REMOVEPRODUCT)
	public ResponseEntity<?> irProduct(@RequestParam(value = "idProduct", required = true) String idProduct) {

		boolean check = cartService.removeProduct(idProduct);
		NotificationResponse notification = new NotificationResponse();
		
		if (check) {
			notification.setMsg("Đã xóa sàn phẩm ra khỏi giỏ hàng");
		} else {
			notification.setMsg("Xóa sản phẩm không thành công");
		}
		
		return new ResponseEntity<>(notification, HttpStatus.OK);
	}
	
}
