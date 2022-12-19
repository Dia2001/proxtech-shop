package com.proxtechshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proxtechshop.common.Constants;

@Controller
public class TransactionController {

	@RequestMapping(value=Constants.ORDERDETAIL_PATH)
	public String OrderDetail(Model model) {
		return Constants.ORDERDETAIL_VIEW;
	}
	@RequestMapping(value=Constants.ORDERS_PATH)
	public String Orders(Model model) {
		return Constants.ORDERS_VIEW;
	}
}
