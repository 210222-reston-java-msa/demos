package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * Front controller to manager-view partials.
 *
 */
@WebServlet("/manager")
public class LoadManagerViewsServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String resourcePath = "partials/" + process(req, resp) + ".html";
		req.getRequestDispatcher(resourcePath).forward(req, resp);
	}
	
	static String process(HttpServletRequest req, HttpServletResponse resp) {
		switch(req.getRequestURI()) {
		case "/project-1/front.managerView":
			return "manager-front";
		case "/project-1/past.managerView":
			return "manager-past";
		case "/project-1/resolved.managerView":
			return "manager-resolved";
		case "/project-1/employees.managerView":
			return "manager-employees";
		case "/project-1/pendingUsers.managerView":
			return "manager-pending-users";
		}			
		return null;
	}
	

}
