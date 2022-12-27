package com.proxtechshop.api.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proxtechshop.services.OrderService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminRestController {
	
	@Autowired
	OrderService orderService;
	@PostMapping("/updateStatus")
	
	public ResponseEntity<?> updateStatus(@RequestParam(value = "orderId", required = true) String orderId,@RequestParam(value = "statusId", required = true) String statusId) {

		System.out.println(orderId+"aaaaaaaaaaaaaa"+statusId);
		boolean check=orderService.updateStatus(orderId, statusId);
		return new ResponseEntity<>(check, HttpStatus.OK);
	}
}
