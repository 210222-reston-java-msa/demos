package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ReimbursementInput;
import com.revature.models.ReimbursementStatusDTO;
import com.revature.services.ReimbursementLogic;

public class UpdateReimbursementStatusServlet extends HttpServlet{

	
	private static final long serialVersionUID = -570352154247044152L;
	private static final Logger logger = LogManager.getLogger(UpdateReimbursementStatusServlet.class);
	
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
		
		
		BufferedReader reader = req.getReader();
		StringBuilder jsonInput = new StringBuilder();
		String line = reader.readLine();
		while(line != null) {
			jsonInput.append(line);
			line = reader.readLine();
		}
		String jsonInputString = jsonInput.toString();
		ReimbursementStatusDTO reimbursementDTO = om.readValue(jsonInputString, ReimbursementStatusDTO.class);
		int rID = reimbursementDTO.getReimID();
		int rStatus = reimbursementDTO.getStatusNum();

		// make this into a conditional statement that executes if true/success or false failed vvvv
		if(rLogic.updateStatus(rID, rStatus, userID)) {
			logger.info("Manager # " + userID + ", changed Reimbursement ID: " + rID + " to Status: " + rStatus);
			res.setStatus(200);
		}
		
	}
}
