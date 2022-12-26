package com.proxtechshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.proxtechshop.common.Constants;
import com.proxtechshop.services.OrderService;
import com.proxtechshop.viewmodels.OrderViewModel;
import com.proxtechshop.viewmodels.ProductDetailViewModel;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = Constants.ORDERS_PATH)
	public ModelAndView getAllOrderByOneCustomer() {

		ModelAndView mav = new ModelAndView(Constants.ORDERS_VIEW);
		List<OrderViewModel> orders = orderService.getOrdersByOneCustomer("","");
		System.out.println(orders.toString());
		mav.addObject("orders", orders);
		return mav;
	}

	@RequestMapping(value = Constants.ORDERDETAIL_PATH, method = RequestMethod.GET)
	public ModelAndView getOneOrderDetailByCustomer(Model model, @PathVariable(value = "id") String id) {
		ModelAndView mav = new ModelAndView(Constants.ORDERDETAIL_VIEW);
		System.out.println(id);
		OrderViewModel order=orderService.getOneOrderByCustomer(id);
		mav.addObject("oneOrder",order);
		return mav;
	}

}
