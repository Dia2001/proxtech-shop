package com.proxtechshop.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.proxtechshop.config.AuthFailureHandlerConfig;

@Configuration
public class AppSecurityBean {
    
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean(name = "authFailureHandler")
	public SimpleUrlAuthenticationFailureHandler authFailureHandler() {
		return new AuthFailureHandlerConfig();
	}

	@Bean(name = "authProvider")
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		return authProvider;
	}
}
