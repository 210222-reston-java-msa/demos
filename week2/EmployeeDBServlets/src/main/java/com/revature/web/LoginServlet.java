package com.revature.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(LoginServlet.class);
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// we will have an index.html with a fomr sectoin/login section and upon clicking th button
		// it will send a POST request to THIS servlet....
		
		// 1. We handle the parameters from the index
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		log.info("User attempted to login with the username " + username);
		// 2. Create an Employee obj to capture the data from DB
		Employee e = EmployeeService.confirmLogin(username, password);
		
		
		// 3. Set an attribute for the session to remember the user
		if (e != null) {
			HttpSession session = request.getSession();
			
			session.setAttribute("username", username);
			
		// 4. Redirect the user to the home page because they successfully logged in
			RequestDispatcher rd = request.getRequestDispatcher("home.html"); // we wil create this ...this welcomes the user upon
			// successful login
			
			rd.forward(request, response);
			log.info(username + " has successfully logged in");
			
		} else {
			log.info(username + "has failed to log in");
			// we will exapnd upon this and create a failure page later and redirect the user there
		}
		
		
	
		
		
		
		
		
		
	}

}
