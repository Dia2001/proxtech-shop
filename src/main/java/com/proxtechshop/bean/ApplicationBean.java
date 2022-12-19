package com.proxtechshop.bean;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.proxtechshop.common.Constants;
import com.proxtechshop.functionalinterface.IUserLogin;
import com.proxtechshop.repositories.UserRepository;

@Configuration
public class ApplicationBean {

	@Autowired
	private UserRepository userRepo;

	@Bean(name = "uploadImageURL")
	public String uploadImageURL() {
		return Constants.UPLOAD_PATH_URL;
	}

	@Bean(name = "homePath")
	public String homePath() {
		return Constants.HOME_PATH;
	}
	
	@Bean(name = "loginPath")
	public String loginPath() {
		return Constants.LOGIN_PATH;
	}

	@Bean(name = "signUpPath")
	public String signUpPath() {
		return Constants.SIGNUP_PATH;
	}
	
	@Bean(name = "logoutPath")
	public String logoutPath() {
		return Constants.LOGOUT_PATH;
	}

	@Bean(name = "productPath")
	public String productPath() {
		return Constants.PRODUCT_PATH;
	}
	
	@Bean(name = "profilePath")
	public String profilePath() {
		return Constants.PROFILE_PATH;
	}

	@Bean(name = "phoneShop")
	public String phoneShop() {
		return "+840123456789";
	}

	@Bean(name = "emailShop")
	public String emailShop() {
		return "contact@prox.tech";
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper;
	}

	@Bean(name = "userLogin")
	public IUserLogin userLogin() {
		return () -> {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				UserDetails userDetails = (UserDetails) auth.getPrincipal();
				return userRepo.getByUsername(userDetails.getUsername());
			} else {
				return null;
			}
		};
	}
}