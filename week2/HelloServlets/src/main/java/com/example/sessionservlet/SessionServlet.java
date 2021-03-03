package com.example.sessionservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.model.SuperVillain;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SessionServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SuperVillain muddy = new SuperVillain("Mud Man", "Mud armor", 100000);
		
		/*
		 * The HttpSession interface provides a way to identify a user
		 * across more than just one page request or visit to a web site and to store info about 
		 * that user.  This makes User experience (UX) better!
		 * 
		 * This doGet() method is using this interface to create a session
		 * between 
		 */
		HttpSession session = request.getSession();
		
		session.setAttribute("villain", muddy);
		
//		response.getWriter().write(new ObjectMapper().writeValueAsString(muddy));
		
		PrintWriter out = response.getWriter();
		out.write(new ObjectMapper().writeValueAsString(muddy));
		out.println("Mud man is on the loose and the session is set"); // this will print to the screen
		
	
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// when I hit doPost() so, I send a POST method to this servlet, I CREATE a new villain object
		// 1. Capture parameters
		String name = request.getParameter("name");
		String superpower = request.getParameter("superpower");
		int bounty = Integer.parseInt(request.getParameter("bounty")); // transforming "120" --> 120 as an int
		
		// 2. Use the captured params to build a supervillain obj
			//	-- This ref var "tempVill" is just a placeholder
		SuperVillain tempVill = new SuperVillain(name, superpower, bounty);
		
		// 3. Grab the HttpSession obj from the request with getSession()
		HttpSession session = request.getSession();
		
		// 4. Set the "villain" attribute = to the custom villain (tempVill)
		session.setAttribute("villain", tempVill);
	
		// 5. Print to the screen that A villain is on the loose
		PrintWriter out = response.getWriter();
		out.println("A villain is on the loose....<br/ >");
		out.write(new ObjectMapper().writeValueAsString(tempVill));
	}

}
