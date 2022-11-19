package com.proxtechshop.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.proxtechshop.common.Constants;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController(Constants.HOME_PATH).setViewName(Constants.HOME_VIEW);
		registry.addViewController(Constants.HOME_PATH_2).setViewName(Constants.HOME_VIEW);
		registry.addViewController(Constants.LOGIN_PATH).setViewName(Constants.LOGIN_VIEW);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory(Constants.UPLOAD_PATH_CONFIG, registry);
	}
	
	private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
		Path uploadDir = Paths.get(dirName);
		String uploadPath = uploadDir.toFile().getAbsolutePath();
		
		if (dirName.startsWith("../")) {
			dirName = dirName.replace("../", "");
		}
		
		registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
	}
}
