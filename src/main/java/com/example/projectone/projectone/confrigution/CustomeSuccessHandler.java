package com.example.projectone.projectone.confrigution;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class CustomeSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		var authorites = authentication.getAuthorities();
		var roles = authorites.stream().map(r -> r.getAuthority()).findFirst();
		
		if (roles.orElse("").equals("ADMIN")) {
			response.sendRedirect("/user/admin");	
		}else if (roles.orElse("").equals("USER")) {
			response.sendRedirect("/user/user");
		}else if (roles.orElse("").equals("STUDENT")) {
			response.sendRedirect("/user/student");
		}else if (roles.orElse("").equals("TEACHER")) {
			response.sendRedirect("/user/teacher");
		}else {
			response.sendRedirect("/extra");
		}
	}

}
