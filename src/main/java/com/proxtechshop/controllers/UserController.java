package com.proxtechshop.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.proxtechshop.common.Constants;
import com.proxtechshop.entities.Customer;
import com.proxtechshop.entities.User;
import com.proxtechshop.repositories.CustomerRepository;
import com.proxtechshop.repositories.UserRepository;
import com.proxtechshop.viewmodels.UserView;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CustomerRepository customerRepo;
	
	@GetMapping(Constants.SIGNUP_PATH)
	public String SignUp(Model model) {
		model.addAttribute("user", new UserView());
		return Constants.SIGNUP_VIEW;
	}
	@GetMapping(Constants.LOGIN_PATH)
	public String Signin(Model model) {
		model.addAttribute("user", new User());
		return Constants.LOGIN_VIEW;
	}
	
	@RequestMapping(value=Constants.POST_REGISTER,method=RequestMethod.POST)
	public RedirectView processRegister(UserView userv, Model model) {
		User user=userRepo.getByUsername(userv.getUsername());
		String msg="";
		if(user==null) {
			user=new User();
			user.setUsername(userv.getUsername());
		    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		    String encodedPassword = passwordEncoder.encode(userv.getPassword());
		    user.setCreatedDate(new Date());
		    user.setPassword(encodedPassword);
		    userRepo.save(user);
		    Customer customer=new Customer();
		    customer.setFullName(userv.getFullname());
		    customer.setEmail(userv.getUsername());
		    customer.setCreatedDate(new Date());
		    User FKUser=userRepo.getByUsername(user.getUsername());
		    customer.setUserId(FKUser.getId());
		    customer.setUser(FKUser);
		    customerRepo.save(customer);
		    model.addAttribute("user",user);
		}
		else {
			msg="Tài khoản đã được tạo";
			 model.addAttribute("user",userv);
			 model.addAttribute("message",msg);
			 return new RedirectView(Constants.SIGNUP_PATH);
		}
	    return new RedirectView(Constants.LOGIN_PATH);
	}
}
