package com.proxtechshop.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proxtechshop.common.Constants;
import com.proxtechshop.models.ProductFilter;
import com.proxtechshop.services.ProductService;
import com.proxtechshop.viewmodels.ProductDetailViewModel;
import com.proxtechshop.viewmodels.ProductPagingViewModel;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(Constants.PRODUCT_PATH)
	public String get(ProductFilter filter, @RequestParam(required = false) Map<String, String> params, Model model) {
		Map<Integer, String[]> attributes = new HashMap<>();
		params.forEach((k, v) -> {
			if (k.startsWith("a")) {
				List<String> values = new ArrayList<>();
				for (String value : v.split(",")) {
					values.add(value.trim());
				}
				attributes.put(Integer.parseInt(k.substring(1)), values.toArray(new String[values.size()]));
			}
		});
		ProductPagingViewModel productPage = productService.getFilter(filter, attributes);
		model.addAttribute("productPage", productPage);
		System.out.println(productPage.toString());
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
