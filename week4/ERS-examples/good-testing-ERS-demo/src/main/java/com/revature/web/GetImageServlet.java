package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementOnlyId;
import com.revature.services.ReimbursementLogic;

public class GetImageServlet extends HttpServlet{

	
	private static final long serialVersionUID = -7236463895472428647L;
	private static final Logger logger = LogManager.getLogger(GetImageServlet.class);
	
	ReimbursementLogic rLogic = new ReimbursementLogic();
	
	private static ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get BufferedReader object from request and create StringBuilder to build the request data
		BufferedReader reader = req.getReader();
		StringBuilder reqInput = new StringBuilder();
		// iterate over the data line by line, append onto the StringBuilder to get all the request data
		String line = reader.readLine();
		while (line != null) {
			reqInput.append(line);
			line = reader.readLine();
		}
		// convert all the data from StringBuilder to actual String
		String reqInputString = reqInput.toString();
		
		// user ObjectMapper to take data and convert into a DTO
		ReimbursementOnlyId idOnly = om.readValue(reqInputString, ReimbursementOnlyId.class);
		
		try {
			// perform my logic
			String receipt = rLogic.getReceiptFromReimId(idOnly.getId());
			
			// create a PrintWriter object from the response, to actually send a response
			PrintWriter output = res.getWriter();
			res.setContentType("application/json");
			res.setStatus(200);
			output.println(receipt);
		} catch (NullPointerException e) {
			
			e.printStackTrace();
			res.setContentType("application/json");
			res.setStatus(204);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			doGet(req,res);
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
	}
	

}
