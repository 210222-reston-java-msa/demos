package com.example.sessionservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelperSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public HelperSessionServlet() {
        super();
    }

    /*
     * This servlet will be used to grab the session when the button is clicked in index.html
     * and it will show us the most recent SuperVillain pushed to the session
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		/*
		 * In doGet() this is where we';; do the work and capture the session, 
		 * 
		 * 
		 * Then we will FORMAT the object we retrieve from the session...
		 * 
		 * like:
		 * 
		 * <h1> Villain name </h1> ..... getName()
		 */
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
