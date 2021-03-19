package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.service.ReimbursementService;

@WebServlet({"/get-pending-by-author","/get-all-by-author", "/get-all-pending", "/get-all-past", "/get-past-by-resolver", "/get-past-by-author"})
public class ReimbursementsServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(ReimbursementsServlet.class);
	private static ReimbursementService rs = new ReimbursementService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		if(user == null) {
			resp.sendRedirect("login");
		}
		else {
			List<Reimbursement> reimbs = new ArrayList<Reimbursement>();
			String requestType = req.getRequestURI();
			logger.trace(requestType);
			switch(requestType) {
			case "/project-1/get-pending-by-author":
				reimbs = rs.getPendingReimbursementsByAuthor(user.getUserId());
				break;
			case "/project-1/get-past-by-author":
				reimbs = rs.getPastReimbursementsByAuthor(user.getUserId());
				break;
			case "/project-1/get-all-by-author":
				reimbs = rs.getReimbursementsByAuthor(user.getUserId());
				break;
			case "/project-1/get-all-pending":
				reimbs = rs.getAllPendingReimbursements();
				break;
			case "/project-1/get-all-past":
				reimbs = rs.getAllPastReimbursements();
				break;
			case "/project-1/get-past-by-resolver":
				reimbs = rs.getReimbursementsByResolver(user.getUserId());
				break;
			}
			
			ObjectMapper mapper = new ObjectMapper();
			String reimsJson = mapper.writeValueAsString(reimbs);
			logger.debug(reimsJson);
			PrintWriter writer = resp.getWriter();
			resp.setContentType("application/json");
			writer.write(reimsJson);

		}
	}


}

