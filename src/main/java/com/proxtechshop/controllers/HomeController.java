package com.proxtechshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proxtechshop.common.Constants;

@Controller
public class HomeController {
	@RequestMapping(Constants.PROFILE_PATH)
	public String Profile(Model model) {
		return Constants.PROFILE_VIEW;
	}
}
