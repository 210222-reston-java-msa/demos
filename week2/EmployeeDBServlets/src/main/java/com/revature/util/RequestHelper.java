package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.LoginTemplate;
import com.revature.services.EmployeeService;

public class RequestHelper {
	
	private static Logger log = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();
	
	public static void processLogin(HttpServletRequest req, HttpServletResponse res) throws IOException { 
		
		// We want to turn whatever we recieve as the request into a string to process
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		// logic to transfer everything from our reader to our string builder
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);
		
		
		// I'm going to build a model called LoginTemplate which holds a username and passwrod
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class); // from JSON --> Java Object

		
		String username = loginAttempt.getUsername();
		String password = loginAttempt.getPassword();	
		
		log.info("User attempted to login with username: " + username);
		Employee e = EmployeeService.confirmLogin(username, password);
			
		if (e != null) {
			// get the current session OR create one if it doesn't exist
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			
			// Attaching the print writer to our response
			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			
			// this is converting our Java Object (with property firstName!) 
			// to JSON format....that means we can grab the firstName property
			// after we parse it. (We parse it in JavaScript)
			pw.println(om.writeValueAsString(e));

			
			log.info(username + " has successfully logged in");	
		} else {
			res.setStatus(204); // this means that we still have a connection, but no user is found
		}
		
		
	}
	
	
	public static void processLogout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession(false); // I'm capturing the session, but if there ISN'T one, I don't create a new one.
		
		
		log.info("Processing logout");
		
		
		if (session != null) {
			
			String username = (String) session.getAttribute("username");
			log.info( username + " has logged out");
							
			session.invalidate();
		}
		
		res.setStatus(200);
	
	}
	
	// This method's purpose is to return all Employees from the DB in JSON form 
	public static void processEmployees(HttpServletRequest req, HttpServletResponse res) throws IOException {
	
		
		// 1. Set the content type to app/json because we will be sending json data back to the client, 
		// stuck alongside the response
		log.info(EmployeeService.findAll());
		res.setContentType("application/json");
		
		// 2. Get a list of all Employees in the DB
		List<Employee> allEmps = EmployeeService.findAll();
		
		// 3. Turn the list of Java Objs into a JSON string
		String json = om.writeValueAsString(allEmps);
		
		// 4. Use getWriter() from the response object to return the json string
		PrintWriter pw = res.getWriter();
		pw.println(json);
		
		
	}	
	
	public static void processError(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		try {
			req.getRequestDispatcher("error.html").forward(req, res);
			// we do NOT create a new request
			// we also maintain the url....
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	
	}
	

}
