package com.proxtechshop.admincontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proxtechshop.common.Constants;
import com.proxtechshop.services.OrderService;

@Controller
public class OrderMngController {
	
	@Autowired
	private OrderService orderService;
	
	//list
	@RequestMapping(Constants.ADMIN_ORDERMNG_PATH)
	public String getListOrders(Model model) {
		model.addAttribute("order",orderService.loadAllOrders());
		return Constants.ADMIN_ORDERMNG_VIEW;
	}
	
	//filter
	@RequestMapping(Constants.ADMIN_ORDERMNG_PATH+"/filter")
	public String fiterOrders(@RequestParam("search")String search,@RequestParam("order_status")String orderStatus,Model model) {
		return Constants.ADMIN_ORDERMNG_VIEW;
	}
	//detail
	@RequestMapping(Constants.ADMIN_ORDERMNG_PATH+"/detail")
	public String detailOrder(@RequestParam("id")String id)
	{
		return Constants.ADMIN_ORDERMNG_VIEW;
	}
	//pagination
	
	
}
