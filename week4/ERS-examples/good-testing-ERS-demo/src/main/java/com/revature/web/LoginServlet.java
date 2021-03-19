package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.models.UserLogin;
import com.revature.models.UserRoles;
import com.revature.services.UserLogic;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(LoginServlet.class);
	UserLogic uLogic = new UserLogic();
	// instantiate ObjectMapper ~~ provides functionality for reading and writing
	// JSON
	private static ObjectMapper om = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			doPost(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		
		// get BufferedReader to read text from char-input stream
		BufferedReader reader = req.getReader();
		// mutable string, concat from reader
		StringBuilder jsonInput = new StringBuilder();
		// parse over the reader object that is reading the request and throw into a
		// single string(builder)
		String line = reader.readLine();
		while (line != null) {
			jsonInput.append(line);
			line = reader.readLine();
		}
		String jsonInputString = jsonInput.toString();
		// turn our JSON from Http Req into an Object in Java
		UserLogin uLogin = om.readValue(jsonInputString, UserLogin.class); // java object representation of info sent in
																			// POST reqest
		String username = uLogin.getUsername();
		String passwordB4 = uLogin.getPassword();
		logger.info("Attempted login with username: " + username);

		try {
			User loggedUser = uLogic.login(username, passwordB4); // checks if user/password is correct and returns
																	// whole user object

			if ((loggedUser != null) && (loggedUser.getRole() == UserRoles.Employee)) { // this is where I should check
																						// if user is a financial
																						// manager
				HttpSession session = req.getSession(); // gets current session or creates one if did not exist
				// give the session a "username" attribute equal to username of uLogin object we
				// created from the JSON
				session.setAttribute("username", username);
				session.setAttribute("userID", loggedUser.getUserId());

				//
				PrintWriter outputStream = res.getWriter(); // prints formatted representations of objects to a text
															// output stream
				res.setContentType("application/json"); // creating the response, and setting the type to JSON
				res.setStatus(200);
				outputStream.println(om.writeValueAsString(loggedUser)); // the response writer, PrintWriter outStream,
																			// prints the values of loggedUser as a
																			// string(JSON??)

				logger.info(username + " succesfully logged in: " + LocalDateTime.now());
			} else if ((loggedUser != null) && (loggedUser.getRole() == UserRoles.FinanceManager)) {
				HttpSession session = req.getSession();
				session.setAttribute("username", username);
				session.setAttribute("userID", loggedUser.getUserId());
				PrintWriter outputStream = res.getWriter();
				res.setContentType("application/json");
				res.setStatus(418);
				outputStream.println(om.writeValueAsString(loggedUser));
			} else { // username or password was incorrect
				logger.info("failed login with username: " + username);
				res.setContentType("application/json");
				res.setStatus(204); // this is that no content status from loginscript
			}
		} catch (NullPointerException e) {
			logger.info("failed login with username: " + username);
			res.setContentType("application/json");
			res.setStatus(204); // this is that no content status from loginscript
		}
	}
}
