package com.example.indirectservlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class IndirectServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(IndirectServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.info("in the INdirect doGet() method");
		
		/*
		 *  there is a method on the response object called sendRedirect()
		 *  this allows us to redirect the user to another resource
		 *  
		 *  -- When you use sendRedirect, a new request is created 
		 */
		
		// redirect to an external page
		//response.sendRedirect("https://www.google.com");
		
		// redirect to another servlet
		// response.sendRedirect("http://localhost:8080/HelloServlets/dirserv");
		
		// redirect to an html page
		response.sendRedirect("resources/html/somepage.html");
		
		// send redirect saves query parameters (in the url)
		
		
	}

	 /* Here is a list of major differences between servlet forward and
	 * Forward:
	 * 
	 * The request will be further processed on the server side 
	 * The client isn't impacted by forward, 
	 * URL in a browser stays the same 
	 * Request and response objects will remain the same object after forwarding. 
	 * Request-scope objects will be still available 
	 * 
	 * 
	 * sendRedirect:
	 * 
	 * The request is redirected to a different resource 
	 * The client will see the URL
	 * change after the redirect 
	 * A new request is created 
	 * Redirect is normally used  within Post/Redirect/Get web development pattern
	 */
	
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 *  Request Dispatcher is an interface whose implementation 
		 *  defines an object which can dispatch the request to any resources on the server.
		*/
		
		// redirect to an html page
		RequestDispatcher rdis = request.getRequestDispatcher("resources/html/somepage.html");
		
		// redirect to another servlet
		//RequestDispatcher rdis = request.getRequestDispatcher("/dirserv");
	
		
		
		rdis.forward(request, response);
		
	}

}
