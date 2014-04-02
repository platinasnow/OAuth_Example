package org.oauth.example.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class PermissionEvaluator implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.sendRedirect(request.getContextPath() + "/accessDenied?error=" + accessDeniedException.getMessage() + "&url=" + request.getParameter("url"));
	}

}
