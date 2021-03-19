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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
	
	private static Logger logger = LogManager.getLogger(LogoutServlet.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("In logout servlet");
		// Returns current session. Does not create new session if one doesn't exist.
		HttpSession session = req.getSession(false);		
		if (session != null) {
			session.invalidate();
			resp.sendRedirect("index.html");
		}
	
	}

}
