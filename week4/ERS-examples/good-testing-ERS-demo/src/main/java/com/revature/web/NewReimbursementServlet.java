package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ReimbursementInput;
import com.revature.services.ReimbursementLogic;

@MultipartConfig
public class NewReimbursementServlet extends HttpServlet{

	private static final long serialVersionUID = 5550131717600337677L;
	private static final Logger logger = LogManager.getLogger(NewReimbursementServlet.class);
	
	ReimbursementLogic rLogic = new ReimbursementLogic();
	
	private static ObjectMapper om = new ObjectMapper();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		try {
			doPost(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		HttpSession session = req.getSession();
		int userID = (int) session.getAttribute("userID");
		InputStream file = null;
		Part filePart = req.getPart("filename");
		if(filePart != null) {
			file = filePart.getInputStream();
		}
		byte[] receipt = IOUtils.toByteArray(file);
		double amount = Double.parseDouble(req.getParameter("amount"));
		int typeNum = Integer.parseInt(req.getParameter("typeNum"));
		String description = req.getParameter("description");
		ReimbursementInput reimbursementInput = new ReimbursementInput(amount, description, typeNum);
		
		
		if(rLogic.createNewReimbursement(reimbursementInput, userID, receipt)) {
			logger.info("User # " + userID + " submitted new reimbursement: Type = " + reimbursementInput.getTypeNum() + "; Amount = $" 
						+ reimbursementInput.getAmount() + "; Description = " + reimbursementInput.getDescription());
			res.setContentType("application/json");
			res.setStatus(201);
			
			res.sendRedirect("/Project1/employee/project1-employee.html");
			
		} else {
			res.setStatus(204);
		}
		
		
	}
}
