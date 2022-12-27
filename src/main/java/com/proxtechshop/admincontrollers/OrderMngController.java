package com.proxtechshop.admincontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proxtechshop.common.Constants;
import com.proxtechshop.services.OrderService;
import com.proxtechshop.viewmodels.OrderViewModel;

@Controller
public class OrderMngController {
	
	@Autowired
	private OrderService orderService;
	
	//list
	@RequestMapping(Constants.ADMIN_ORDERMNG_PATH)
	public String getListOrders(Model model) {
		model.addAttribute("orders",orderService.loadAllOrders());
		model.addAttribute("orderStatuss",orderService.loadAddOrderStatus());
		System.out.println(orderService.loadAllOrders().toString());
		return Constants.ADMIN_ORDERMNG_VIEW;
	}
	
	//filter
	@RequestMapping(Constants.ADMIN_ORDERMNG_PATH+"/filter")
	public String fiterOrders(@RequestParam("search")String search,@RequestParam("order_status")String orderStatus,Model model) {
		return Constants.ADMIN_ORDERMNG_VIEW;
	}
	//detail
	@RequestMapping(Constants.ADMIN_ORDERMNG_PATH+"/detail/{id}")
	public ModelAndView detailOrder(Model model, @PathVariable(value = "id") String id)
	{
		ModelAndView mav = new ModelAndView(Constants.ADMIN_ORDERMNG_VIEW_PATH);
		System.out.println(id);
		OrderViewModel order=orderService.getOneOrderByCustomer(id);
		mav.addObject("oneOrder",order);
		mav.addObject("orderStatuss",orderService.loadAddOrderStatus());
		return mav;
	}
	//pagination
	
	
}
