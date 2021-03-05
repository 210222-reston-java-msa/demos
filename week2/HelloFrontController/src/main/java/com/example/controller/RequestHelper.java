package com.example.controller;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

	public static String process(HttpServletRequest request) {

		System.out.println(request.getRequestURI());

		switch (request.getRequestURI()) {

			case "/HelloFrontController/login.do":
	
				return LoginController.login(request);
	
			case "/HelloFrontController/home.do":
	
				return HomeController.home(request);
	
			default:
	
				return "resources/html/unsuccessfullogin.html";
		}
	}
}
