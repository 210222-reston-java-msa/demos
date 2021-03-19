package com.revature.web;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.revature.servlethelper.ReimbursementHelper;
import com.revature.servlethelper.UserHelper;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();

		try {
			

			HttpSession session = req.getSession();
			String sessionUserId = session.getAttribute("userId").toString();

			if (sessionUserId == null || !req.getMethod().equals("GET")) {

				params.addProperty("status", "unauthorized access");
				String json = gson.toJson(params);
				pw.write(json);
				return;			
			}

			final String URI = req.getRequestURI().replaceAll("/project-1/", "");

			switch (URI) {						
			case "getUser":
				UserHelper.getUser(req, resp);
				return;
			case "getAllUsers":
				UserHelper.getAllUsers(req, resp);
				return;
			default:
				return;
			}

		} catch (Exception e) {
			params.addProperty("status", "unauthorized access");
			String json = gson.toJson(params);
			pw.write(json);		
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();
		
		try {

			HttpSession session = req.getSession();
			String sessionUserId = session.getAttribute("userId").toString();
			System.out.println("session user id id doPOST: " +  sessionUserId);

			if (sessionUserId == null || !req.getMethod().equals("POST")) {

				params.addProperty("status", "unauthorized access");
				String json = gson.toJson(params);
				pw.write(json);
				return;			
			}

			final String URI = req.getRequestURI().replace("/project-1/", "");

			switch (URI) {

			case "home":
				ReimbursementHelper.getAllReimbursementsForChart(req, resp);
				return;	
			case "addReimb":
				ReimbursementHelper.addReimbursement(req, resp);
				return;
			case "getAllReimbs":
				ReimbursementHelper.getAllReimbursements(req, resp);
				return;
			case "getReimbsByStatus":
				ReimbursementHelper.getReimbsByStatus(req, resp);
				return;				
			case "updateUser":
				UserHelper.updateUser(req, resp);
				return;
			case "approveReimb":
				ReimbursementHelper.approveReimb(req, resp);
				return;
			case "denyReimb":
				ReimbursementHelper.denyReimb(req, resp);
				return;
			case "searchReimb":
				ReimbursementHelper.searchReimb(req, resp);
				return;
			case "getReceipt":
				ReimbursementHelper.getReceipt(req, resp);
				return;
			case "feedback":
				UserHelper.feedback(req, resp);
				return;
				
			default:
				return;
			}

		} catch (Exception e) {
			params.addProperty("status", "unauthorized access");
			String json = gson.toJson(params);
			pw.write(json);
		}

	}

}
