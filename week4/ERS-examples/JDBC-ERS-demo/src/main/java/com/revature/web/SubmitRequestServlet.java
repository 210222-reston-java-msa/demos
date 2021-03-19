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

@WebServlet("/submit-request")
public class SubmitRequestServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(SubmitRequestServlet.class);
	ReimbursementService rs = new ReimbursementService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("In SubmitRequestServlet");
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		int author = user.getUserId();
	
		ObjectMapper mapper = new ObjectMapper();
		// Incomplete object does not include a userId
		Reimbursement incomplete = mapper.readValue(req.getInputStream(), Reimbursement.class);
		Reimbursement r = new Reimbursement(author, incomplete.getAmount(), incomplete.getrType(), incomplete.getrDesc());
		r = rs.submit(r);
		logger.info("Created a reimbursement request: " + r);
				
	}
}
