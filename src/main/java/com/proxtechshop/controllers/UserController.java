package com.proxtechshop.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.proxtechshop.common.Constants;
import com.proxtechshop.services.UserService;
import com.proxtechshop.viewmodels.CustomUserModelView;
import com.proxtechshop.viewmodels.UserView;

@Controller
public class UserController {

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
		ModelAndView page = new ModelAndView();

		boolean register = userService.Register(userv);
		if (register) {
			page.addObject("user", userv);
			page.setViewName(Constants.LOGIN_VIEW);
		} else {
			page.addObject("user", userv);
			page.addObject("message", "Tài khoản đã dược tạo!");
			page.setViewName(Constants.SIGNUP_VIEW);
		}
		return page;
	}

	@RequestMapping(Constants.PROFILE_PATH)
	public String Profile(Model model) {
		CustomUserModelView mv = userService.loadProfile();
		System.out.println(mv);
		model.addAttribute("user", mv);
		return Constants.PROFILE_VIEW;
	}

	@RequestMapping(value = Constants.PROFILE_PATH, method = RequestMethod.POST)
	public ModelAndView UpdateProfile(CustomUserModelView userv, @RequestParam(name = "avatar") MultipartFile file)
			throws IOException {
		ModelAndView page = new ModelAndView();
		boolean flag = userService.UpdateProfile(userv, file);

		if (flag) {
			page.addObject("user", userService.loadProfile());
			page.addObject("message", "Đã thay đổi thành công!");
			page.addObject("flag", flag);

		} else {
			page.addObject("user", userv);
			page.addObject("message", "Thay đổi không thành công!");
			page.addObject("flag", flag);

		}
		page.setViewName(Constants.PROFILE_VIEW);
		return page;
	}

	@RequestMapping(value = Constants.CHANGEPASS_URL_PATH, method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView changePassWord(@RequestParam(name = "oldpass") String oldPass,
			@RequestParam(name = "newpass") String newPass) {
		ModelAndView page = new ModelAndView();
		CustomUserModelView userv = userService.loadProfile();
		page.addObject("user", userv);
		boolean flag = userService.changePassword(oldPass, newPass);

		if (flag) {
			page.addObject("message", "Thay đổi mật khẩu thành công!");
			page.addObject("flag", flag);
			page.setViewName(Constants.PROFILE_VIEW);
		} else {
			page.addObject("message", "Lỗi khi thay đổi mật khẩu!");
			page.addObject("flag", flag);
			page.setViewName(Constants.PROFILE_VIEW);
		}

		return page;
	}

}
