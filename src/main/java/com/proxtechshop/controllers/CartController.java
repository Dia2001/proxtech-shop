package com.proxtechshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proxtechshop.common.Constants;
import com.proxtechshop.services.CartService;
import com.proxtechshop.viewmodels.CartViewModel;

@Controller
public class CartController {

	@Autowired
	CartService cartService;
	
	@RequestMapping(Constants.CART_PATH)
	public ModelAndView getAllProudctToCustomer() {
		ModelAndView mav = new ModelAndView(Constants.CART_VIEW);
		List<CartViewModel> carts = cartService.getAllProductToCustomer();
		mav.addObject("carts",carts);
		System.out.print(carts);
		return mav;
	}
	
}
