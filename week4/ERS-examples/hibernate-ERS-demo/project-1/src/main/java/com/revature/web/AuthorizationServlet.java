package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.servlethelper.AuthorizationHelper;
import com.revature.servlethelper.UserHelper;

public class AuthorizationServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		
		final String URI = req.getRequestURI().replace("/project-1/", "");
		
		switch (URI) {
		case "signin":
			AuthorizationHelper.signin(req, resp);
			return;
			
		case "signout":
			AuthorizationHelper.signout(req, resp);
			return;
			
		case "signup":
			UserHelper.signup(req, resp);
			return;
			
		case "forgotPass":
			AuthorizationHelper.forgotPass(req, resp);
			return;
			
		default:
			break;
		}
	}
	
}
