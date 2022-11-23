package com.proxtechshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proxtechshop.common.Constants;

@Controller
public class HomeController {
	@GetMapping(Constants.SIGNUP_PATH)
	public String SignUp(Model model) {
		return Constants.SIGNUP_VIEW;
	}
}
