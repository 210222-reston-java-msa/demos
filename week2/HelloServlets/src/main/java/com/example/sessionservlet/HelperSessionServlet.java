package com.example.sessionservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.model.SuperVillain;

// /helpersesserv
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
		
		HttpSession session = request.getSession();
		
		SuperVillain villain = (SuperVillain) session.getAttribute("villain");
		
		// We are creating a dynamic html page on the fly
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		
			if (villain != null) {
				
				out.println("We've captured the villain!");
				
				out.println("<h1>Villain Name: " + villain.getName() + "</h1><br/>");
				out.println("<b>Superpower: " + villain.getSuperpower() + "</b><br/>");
				out.println("<i>Bounty: $" + villain.getBounty() + "</i>");
				
			} else {
				// do something here if no villain was fetched
				out.println("<i>Can't find any villains...</i>");
				
			}
		
		out.println("</body></html>");

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
