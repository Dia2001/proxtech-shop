package com.proxtechshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.proxtechshop.common.Constants;
import com.proxtechshop.services.ProductService;
import com.proxtechshop.services.UserService;
import com.proxtechshop.viewmodels.ProductDetailViewModel;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(Constants.PRODUCT_PATH)
	public String get() {
		return Constants.PRODUCT_VIEW;
	}

	@RequestMapping(value = Constants.PRODUCT_DETAIL_URL_PATH, method = RequestMethod.GET)
	public ModelAndView getOneProductDetail(Model model, @PathVariable(value = "id") String id) {
		ModelAndView mav = new ModelAndView(Constants.PRODUCT_DETAIL_VIEW);
		ProductDetailViewModel product = productService.getOneProductDeTail(id);
		mav.addObject("productDetail", product);
		return mav;
	}

}
