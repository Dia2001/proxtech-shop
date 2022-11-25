package com.proxtechshop.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.proxtechshop.common.Constants;

public class AuthFailureHandlerConfig extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		getRedirectStrategy().sendRedirect(request, response, Constants.LOGIN_PATH + "?error=" + exception.getMessage()
				+ "&username=" + request.getParameter("username"));
	}
}