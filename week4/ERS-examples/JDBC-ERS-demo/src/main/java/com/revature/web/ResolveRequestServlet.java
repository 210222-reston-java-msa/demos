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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.service.ReimbursementService;

@WebServlet({"/approve-request", "/deny-request"})
public class ResolveRequestServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(ResolveRequestServlet.class);
	private static ReimbursementService rs = new ReimbursementService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		if(user == null) {
			resp.sendRedirect("login");
		}		
		else {
			ObjectMapper mapper = new ObjectMapper();
			String requestType = req.getRequestURI();
			logger.trace(requestType);
			Reimbursement onlyId = mapper.readValue(req.getInputStream(), Reimbursement.class);
			int id = onlyId.getrId();
			Reimbursement r = rs.getReimbursementById(id);
			switch(requestType) {
			case "/project-1/approve-request":
				r.setrStatus(2);	// 2 = APPROVED	
				break;
			case "/project-1/deny-request":
				r.setrStatus(3);	// 3 = DENIED
				break;
			}
			r.createResolveDate();
			r.setrResolver(user.getUserId());
			rs.update(r);
		}
	}
}
