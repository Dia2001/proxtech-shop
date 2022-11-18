package com.proxtechshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.proxtechshop.common.Constants;

@Controller
public class ProductController {
	
	@RequestMapping(Constants.PRODUCT_PATH)
	public String get() {
		return Constants.PRODUCT_VIEW;
	}
}
