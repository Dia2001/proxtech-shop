package com.proxtechshop.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.proxtechshop.common.Constants;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SimpleUrlAuthenticationFailureHandler authFailureHandler;
	
	@Autowired
	public DaoAuthenticationProvider authProvider;
	
	private void loginSuccessHandler(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws Exception {
		// do something when after login success
		response.sendRedirect(Constants.HOME_PATH);
	}

	private void logoutSuccessHandler(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws Exception {
		// do something when after logout success
		response.sendRedirect(Constants.HOME_PATH);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers(Constants.HOME_PATH).permitAll()
				.antMatchers(HttpMethod.GET, Constants.PRODUCT_DETAIL_PATH).permitAll()
				.antMatchers(HttpMethod.GET, Constants.PRODUCT_DETAIL_URL_PATH).permitAll()
				.antMatchers(HttpMethod.POST, Constants.CART_PATH).permitAll()
				.antMatchers(HttpMethod.POST, Constants.CART_URL_ACTION).permitAll()
				.antMatchers(HttpMethod.POST, Constants.PAYMENT_PRODUCTDETAIL_URL_API).permitAll()
				.antMatchers(Constants.UPLOAD_RESOURCE_PATH_CONFIG).permitAll()
				.antMatchers(Constants.STATIC_RESOURCE_PATH_CONFIG).permitAll()
				.antMatchers(Constants.SIGNUP_PATH).permitAll()
				.antMatchers(HttpMethod.POST,Constants.REGISTER_URL_PATH).permitAll()
				.antMatchers(Constants.ADMIN_PATH).permitAll()
				.antMatchers(Constants.ORDERDETAIL_PATH).permitAll()
				.antMatchers(Constants.PAYMENT_PATH).permitAll()
				.antMatchers(Constants.ORDERS_PATH).permitAll()
				.antMatchers(Constants.PROFILE_PATH).authenticated();
				
		http.authorizeRequests()
				.anyRequest().permitAll();
		
		http.formLogin().loginPage(Constants.LOGIN_PATH).permitAll()
				.successHandler((request, response, authentication) -> {
					try {
						loginSuccessHandler(request, response, authentication);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}).failureHandler(authFailureHandler);

		http.logout(logout -> logout
				.logoutUrl(Constants.LOGOUT_PATH)
				.logoutSuccessHandler((request, response, authentication) -> {
					try {
						logoutSuccessHandler(request, response, authentication);
					} catch (Exception e) {
						e.printStackTrace();
					}
				})
				.deleteCookies()
		).logout().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}
}
