package com.proxtechshop.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proxtechshop.common.Constants;

@Controller
public class AuthController {
	@RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameTest(Principal principal) {
        return principal.getName();
    }
	@RequestMapping(value=Constants.ADMIN_PATH)
	public String Admin(Model model) {
		return Constants.ADMIN_VIEW;
	}
}
