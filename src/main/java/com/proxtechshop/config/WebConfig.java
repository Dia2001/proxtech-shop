package com.proxtechshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.proxtechshop.common.Constants;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController(Constants.HOME_PATH).setViewName(Constants.HOME_VIEW);
		registry.addViewController(Constants.HOME_PATH_2).setViewName(Constants.HOME_VIEW);
//		registry.addViewController(Constants.LOGIN_PATH).setViewName(Constants.LOGIN_VIEW);
	}
}
