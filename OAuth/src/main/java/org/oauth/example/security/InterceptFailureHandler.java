package org.oauth.example.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class InterceptFailureHandler implements AuthenticationEntryPoint {
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authExcetpion) throws IOException,
			ServletException {
		response.sendRedirect(request.getContextPath() + "/interceptFailure?error=" + authExcetpion.getMessage() + "&url="
				+ request.getParameter("url"));
	}
}
