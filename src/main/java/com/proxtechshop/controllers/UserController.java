package com.proxtechshop.controllers;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.view.RedirectView;

import com.proxtechshop.common.Constants;
import com.proxtechshop.entities.User;
import com.proxtechshop.repositories.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping(Constants.SIGNUP_PATH)
	public String SignUp(Model model) {
		model.addAttribute("user", new User());
		return Constants.SIGNUP_VIEW;
	}
	
	
	@RequestMapping(value=Constants.POST_REGISTER,method=RequestMethod.POST)
	@ResponseBody
	public RedirectView processRegister(User user, Model model) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setCreatedDate(new Date());
	    user.setPassword(encodedPassword);
	    userRepo.save(user);
	    model.addAttribute("username",user.getUsername());
	    return new RedirectView(Constants.LOGIN_PATH);
	}
}
