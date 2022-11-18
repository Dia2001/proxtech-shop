package com.proxtechshop.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.proxtechshop.common.Constants;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoderBean() {
    	return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoderBean());
        return authProvider;
    }
    
    public void logoutSuccessHandler() {
    	// do something when after logout success
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(Constants.HOME_PATH).permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage(Constants.LOGIN_PATH)
            .permitAll()
            .and()
            .logout()
            .permitAll();
        
        http
        	.logout(logout -> logout
        			.logoutUrl(Constants.LOGOUT_PATH)
        			.logoutSuccessUrl(Constants.LOGOUT_PATH_SUCCESS)
        			.deleteCookies()
        	);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
}
