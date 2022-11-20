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

	@RequestMapping(Constants.PRODUCT_DETAIL_URL_PATH)
	public String getDetail() {
		return Constants.PRODUCT_DETAIL_VIEW;
	}
}
