package com.proxtechshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proxtechshop.common.Constants;
import com.proxtechshop.repositories.CustomerRepository;
import com.proxtechshop.repositories.UserRepository;
import com.proxtechshop.services.UserService;
import com.proxtechshop.viewmodels.UserView;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private UserService userService;

	@GetMapping(Constants.SIGNUP_PATH)
	public String SignUp(Model model) {
		model.addAttribute("user", new UserView());
		return Constants.SIGNUP_VIEW;
	}

	@GetMapping(Constants.LOGIN_PATH)
	public String Signin(@RequestParam(required = false) String username, Model model) {
		UserView user = new UserView();
		user.setUsername(username);
		model.addAttribute("user", user);
		return Constants.LOGIN_VIEW;
	}

	@RequestMapping(value = Constants.REGISTER_URL_PATH, method = RequestMethod.POST)
	public ModelAndView processRegister(UserView userv, Model model) {
		ModelAndView page=new ModelAndView();
		
		boolean register=userService.Register(userv);
		if(register)
		{
			page.addObject("user", userv);
			page.setViewName(Constants.LOGIN_VIEW);
		}
		else {
			page.addObject("user", userv);
			page.addObject("message","Tài khoản đã dược tạo!");
			page.setViewName(Constants.SIGNUP_VIEW);
		}
		return page;
	}
	
	@RequestMapping(Constants.PROFILE_PATH)
	public String Profile(Model model) {
		return Constants.PROFILE_VIEW;
	}
}
