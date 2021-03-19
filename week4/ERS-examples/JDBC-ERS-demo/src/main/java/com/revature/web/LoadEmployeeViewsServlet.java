package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * Front controller to employee-view partials.
 *
 */
public class LoadEmployeeViewsServlet extends HttpServlet{
	private static Logger logger = LogManager.getLogger(LoadEmployeeViewsServlet.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String resourcePath = "partials/" + process(req, resp) + ".html";
		logger.debug(resourcePath);
		req.getRequestDispatcher(resourcePath).forward(req, resp);
	}
	
	static String process(HttpServletRequest req, HttpServletResponse resp) {
		logger.trace("In LoadEmployeeViews Servlet process()");
		switch(req.getRequestURI()) {
		case "/project-1/front.employeeView":
			return "employee-front";
		case "/project-1/all.employeeView":
			return "employee-all";
		case "/project-1/past.employeeView":
			return "employee-past";
		case "/project-1/submit.employeeView":
			return "employee-submit";
		case "/project-1/request-submitted.employeeView":
			return "employee-request-submitted";
		}
			
		return null;
	}
}

