  
package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.User;
import com.revature.service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static UserService us = new UserService();
	private static Logger logger = LogManager.getLogger(LoginServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Functionality to go back to welcome page
		req.getRequestDispatcher("index.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Login functionality
		String email = req.getParameter("inputEmail");
		String password = req.getParameter("inputPassword");
		logger.info(email);
		logger.info(password);
		
		// Validate user
		User user = us.getUserByEmailPassword(email, password);
		logger.trace(user);
		// Go back to login page if wrong email/pw combo OR if user is not approved
		if (user == null || user.getApproved() == 0) {			
			req.getRequestDispatcher("index.html").forward(req, resp);
		}
		else {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			logger.trace("ADDING USER TO SESSION: " + session.getId());		
			// Employee login
			if (user.getRoll() == 1) {
				resp.sendRedirect("load-employee-home");
			}
			// Manager login
			else if (user.getRoll() == 2) {
				resp.sendRedirect("load-manager-home");
			}
		}
	}

}