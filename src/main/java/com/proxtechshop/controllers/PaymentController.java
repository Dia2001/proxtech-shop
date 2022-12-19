package com.proxtechshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proxtechshop.common.Constants;
import com.proxtechshop.services.PaymentService;
import com.proxtechshop.viewmodels.PaymentCustomerDetailViewModel;
@Controller
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	
	@RequestMapping(value=Constants.PAYMENT_PATH)
	public ModelAndView Payment(Model model) {
		ModelAndView mav = new ModelAndView(Constants.PAYMENT_VIEW);
		PaymentCustomerDetailViewModel inforCustomer=paymentService.inforCustomerPayment();
		mav.addObject("infoCustomer", inforCustomer);
		return mav;
	}

}
