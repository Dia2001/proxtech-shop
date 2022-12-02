package com.proxtechshop.utils;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class GetUserUtil {
	public static String GetUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		if (principal instanceof UserDetails) {
		username = ((UserDetails)principal).getUsername();
		} else {
		username = principal.toString();
		}
		return username;
	}
}
