package com.revature.servlethelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementServices;
import com.revature.services.ReimbursementServicesImpl;
import com.revature.services.UserServices;
import com.revature.services.UserServicesImpl;
import com.revature.util.SendEmail;

public class ReimbursementHelper {

	private static ReimbursementServices reimbService = new ReimbursementServicesImpl();
	private static UserServices userService = new UserServicesImpl();

	public static void addReimbursement(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();

		try {

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();

			HttpSession session = req.getSession();
			int userId = (int) session.getAttribute("userId");

			int clientUserId = rootobj.get("userId").getAsInt();

			if (clientUserId != userId) {

				params.addProperty("status", "unauthorized access");
				String json = gson.toJson(params);
				pw.write(json);
				return;
			}

			double reimbAmount = rootobj.get("amount").getAsDouble();
			LocalDateTime reimbSubmitted = LocalDateTime.now();
			String reimbDescription = rootobj.get("description").getAsString();
			int typeId = rootobj.get("typeId").getAsInt();
			String base64 = rootobj.get("base64").getAsString();

			byte[] reimbReceipt = base64.getBytes();

			User user = new User();
			user.setUserId(userId);
			Reimbursement reimb = new Reimbursement(reimbAmount, reimbSubmitted, reimbDescription, reimbReceipt, user,
					new ReimbStatus(1, "Pending"), new ReimbType(typeId));

			if (reimbService.addReimbursement(reimb)) {

				String json = gson.toJson(reimb);
				pw.write(json);

			} else {
				params.addProperty("status", "process failed");
				String json = gson.toJson(params);
				pw.write(json);
			}

		} catch (Exception e) {
			params.addProperty("status", "process failed");
			String json = gson.toJson(params);
			pw.write(json);
		}

	}

	public static void getAllReimbursements(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params1 = new JsonObject();

		try {

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();

			HttpSession session = req.getSession();
			int userId = (int) session.getAttribute("userId");
			int clientUserId = rootobj.get("userId").getAsInt();

			if (clientUserId != userId) {

				params1.addProperty("status", "unauthorized access");
				String json = gson.toJson(params1);
				pw.write(json);
				return;
			}

			List<Reimbursement> reimbs = reimbService.getAllReimbursements(userId);

			if (reimbs == null || reimbs.size() == 0) {
				params1.addProperty("status", "no record");
				String json = gson.toJson(params1);
				pw.write(json);
			} else {

				JsonArray jobj = new JsonArray();
				for (Reimbursement reimb : reimbs) {
					JsonObject params = new JsonObject();
					params.addProperty("reimbId", reimb.getReimbId());
					params.addProperty("reimbAmount", reimb.getReimbAmount());
					params.addProperty("reimbDescription", reimb.getReimbDescription());

					if (reimb.getReimbResolved() == null) {
						params.addProperty("reimbResolved", "Not Processed Yet");
					} else {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
						String reimbResolvedFormated = reimb.getReimbResolved().format(formatter);
						params.addProperty("reimbResolved", reimbResolvedFormated);
					}

					if (reimb.getReimbSubmitted() == null) {
						params.addProperty("reimbSubmitted", "Not Processed Yet");
					} else {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
						String reimbSubmittedFormated = reimb.getReimbSubmitted().format(formatter);
						params.addProperty("reimbSubmitted", reimbSubmittedFormated);
					}

					params.addProperty("reimbAuthor",
							reimb.getReimbAuthor().getFirstName() + " " + reimb.getReimbAuthor().getLastName());

					if (reimb.getReimbResolver() == null) {
						params.addProperty("reimbResolver", "Not Assigned Yet");
					} else {
						params.addProperty("reimbResolver",
								reimb.getReimbResolver().getFirstName() + " " + reimb.getReimbResolver().getLastName());
					}

					params.addProperty("reimbStatus", reimb.getReimbStatus().getStatus());
					params.addProperty("reimbType", reimb.getReimbType().getType());

					jobj.add(params);
				}

				pw.write(jobj.toString());
			}
		} catch (Exception e) {
			params1.addProperty("status", "process failed");
			String json = gson.toJson(params1);
			pw.write(json);
		}

	}

	public static void getReimbsByStatus(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params1 = new JsonObject();

		try {

			HttpSession session = req.getSession();
			int userId = (int) session.getAttribute("userId");

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();

			String status = rootobj.get("status").getAsString();
			int clientUserId = rootobj.get("userId").getAsInt();

			if (clientUserId != userId) {

				params1.addProperty("status", "unauthorized access");
				String json = gson.toJson(params1);
				pw.write(json);
				return;
			}

			List<Reimbursement> reimbs = reimbService.getReimbsByStatus(userId, status);

			if (reimbs == null || reimbs.size() == 0) {
				params1.addProperty("status", "no record");
				String json = gson.toJson(params1);
				pw.write(json);
			} else {

				JsonArray jobj = new JsonArray();
				for (Reimbursement reimb : reimbs) {
					JsonObject params = new JsonObject();
					params.addProperty("reimbId", reimb.getReimbId());
					params.addProperty("reimbAmount", reimb.getReimbAmount());
					params.addProperty("reimbDescription", reimb.getReimbDescription());

					if (reimb.getReimbResolved() == null) {
						params.addProperty("reimbResolved", "Not Processed Yet");
					} else {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
						String reimbResolvedFormated = reimb.getReimbResolved().format(formatter);
						params.addProperty("reimbResolved", reimbResolvedFormated);
					}

					if (reimb.getReimbSubmitted() == null) {
						params.addProperty("reimbSubmitted", "Not Processed Yet");
					} else {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
						String reimbSubmittedFormated = reimb.getReimbSubmitted().format(formatter);
						params.addProperty("reimbSubmitted", reimbSubmittedFormated);
					}

					params.addProperty("reimbAuthor",
							reimb.getReimbAuthor().getFirstName() + " " + reimb.getReimbAuthor().getLastName());

					if (reimb.getReimbResolver() == null) {
						params.addProperty("reimbResolver", "Not Assigned Yet");
					} else {
						params.addProperty("reimbResolver",
								reimb.getReimbResolver().getFirstName() + " " + reimb.getReimbResolver().getLastName());
					}

					params.addProperty("reimbStatus", reimb.getReimbStatus().getStatus());
					params.addProperty("reimbType", reimb.getReimbType().getType());

					jobj.add(params);

				}

				pw.write(jobj.toString());
			}

		} catch (Exception e) {
			params1.addProperty("status", "process failed");
			String json = gson.toJson(params1);
			pw.write(json);
		}

	}

	@SuppressWarnings("static-access")
	public static void approveReimb(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();

		try {

			HttpSession session = req.getSession();
			int userId = (int) session.getAttribute("userId");

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();

			int reimbId = rootobj.get("reimbId").getAsInt();
			int clientUserId = rootobj.get("userId").getAsInt();

			if (clientUserId != userId) {

				params.addProperty("status", "unauthorized access");
				String json = gson.toJson(params);
				pw.write(json);
				return;
			}

			boolean isApproved = reimbService.approveReimb(reimbId, userId);

			if (isApproved) {

				String toEmail = userService.getEmail(reimbId);
				System.out.println("Employee Email: " + toEmail);

				Reimbursement reimb = reimbService.getReimb(reimbId);
				String status = "Approved";

				SendEmail email = new SendEmail();
				email.sendEmail(reimb, toEmail, status);
				String emailStatus = "Email Success";

				params.addProperty("status", status);
				params.addProperty("email", emailStatus);

				String json = gson.toJson(params);
				pw.write(json);

			} else {
				params.addProperty("status", "process failed");
				String json = gson.toJson(params);
				pw.write(json);
			}

		} catch (Exception e) {
			params.addProperty("status", "process failed");
			String json = gson.toJson(params);
			pw.write(json);
		}

	}

	@SuppressWarnings("static-access")
	public static void denyReimb(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();

		try {

			HttpSession session = req.getSession();
			int userId = (int) session.getAttribute("userId");

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();

			int reimbId = rootobj.get("reimbId").getAsInt();
			int clientUserId = rootobj.get("userId").getAsInt();

			if (clientUserId != userId) {

				params.addProperty("status", "unauthorized access");
				String json = gson.toJson(params);
				pw.write(json);
				return;
			}

			boolean isDenied = reimbService.denyReimb(reimbId, userId);

			if (isDenied) {

				String toEmail = userService.getEmail(reimbId);
				System.out.println("Employee Email: " + toEmail);

				Reimbursement reimb = reimbService.getReimb(reimbId);
				String status = "Denied";

				SendEmail email = new SendEmail();
				email.sendEmail(reimb, toEmail, status);
				String emailStatus = "Email Success";

				params.addProperty("status", status);
				params.addProperty("email", emailStatus);

				String json = gson.toJson(params);
				pw.write(json);

			} else {
				params.addProperty("status", "process failed");
				String json = gson.toJson(params);
				pw.write(json);
			}

		} catch (Exception e) {
			params.addProperty("status", "process failed");
			String json = gson.toJson(params);
			pw.write(json);

		}

	}

	public static void searchReimb(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params1 = new JsonObject();

		try {

			HttpSession session = req.getSession();
			int userId = (int) session.getAttribute("userId");

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();

			int clientUserId = rootobj.get("userId").getAsInt();
			int employeeId = rootobj.get("employeeId").getAsInt();

			if (clientUserId != userId) {

				params1.addProperty("status", "unauthorized access");
				String json = gson.toJson(params1);
				pw.write(json);
				return;
			}

			List<Reimbursement> reimbs = reimbService.searchReimb(employeeId);

			if (reimbs == null || reimbs.size() == 0) {
				params1.addProperty("status", "no record");
				String json = gson.toJson(params1);
				pw.write(json);
			} else {

				JsonArray jobj = new JsonArray();
				for (Reimbursement reimb : reimbs) {
					JsonObject params = new JsonObject();
					params.addProperty("reimbId", reimb.getReimbId());
					params.addProperty("reimbAmount", reimb.getReimbAmount());
					params.addProperty("reimbDescription", reimb.getReimbDescription());

					if (reimb.getReimbResolved() == null) {
						params.addProperty("reimbResolved", "Not Processed Yet");
					} else {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
						String reimbResolvedFormated = reimb.getReimbResolved().format(formatter);
						params.addProperty("reimbResolved", reimbResolvedFormated);
					}

					if (reimb.getReimbSubmitted() == null) {
						params.addProperty("reimbSubmitted", "Not Processed Yet");
					} else {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
						String reimbSubmittedFormated = reimb.getReimbSubmitted().format(formatter);
						params.addProperty("reimbSubmitted", reimbSubmittedFormated);
					}

					params.addProperty("reimbAuthor",
							reimb.getReimbAuthor().getFirstName() + " " + reimb.getReimbAuthor().getLastName());

					if (reimb.getReimbResolver() == null) {
						params.addProperty("reimbResolver", "Not Assigned Yet");
					} else {
						params.addProperty("reimbResolver",
								reimb.getReimbResolver().getFirstName() + " " + reimb.getReimbResolver().getLastName());
					}

					params.addProperty("reimbStatus", reimb.getReimbStatus().getStatus());
					params.addProperty("reimbType", reimb.getReimbType().getType());

					jobj.add(params);
				}

				pw.write(jobj.toString());
			}

		} catch (Exception e) {
			params1.addProperty("status", "process failed");
			String json = gson.toJson(params1);
			pw.write(json);
		}

	}

	public static void getReceipt(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();

		try {

			HttpSession session = req.getSession();
			int userId = (int) session.getAttribute("userId");

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();

			int clientUserId = rootobj.get("userId").getAsInt();
			int reimbId = rootobj.get("reimbId").getAsInt();

			if (clientUserId != userId) {

				params.addProperty("status", "unauthorized access");
				String json = gson.toJson(params);
				pw.write(json);
				return;
			}

			Reimbursement reimb = reimbService.getReceipt(reimbId);

			byte[] reimbReceipt = reimb.getReimbReceipt();
			String base64 = new String(reimbReceipt);

			if (base64 == null || base64.length() == 0) {
				params.addProperty("status", "no record");
				String json = gson.toJson(params);
				pw.write(json);
			} else {
				params.addProperty("base64", base64);
				String json = gson.toJson(params);
				pw.write(json);
			}

		} catch (Exception e) {
			params.addProperty("status", "process failed");
			String json = gson.toJson(params);
			pw.write(json);
		}

	}
	
	public static void getAllReimbursementsForChart(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params1 = new JsonObject();

		try {

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();

			HttpSession session = req.getSession();
			int userId = (int) session.getAttribute("userId");
			int clientUserId = rootobj.get("userId").getAsInt();

			if (clientUserId != userId) {

				params1.addProperty("status", "unauthorized access");
				String json = gson.toJson(params1);
				pw.write(json);
				return;
			}

			List<Reimbursement> reimbs = reimbService.getAllReimbursementsForChart(userId);

			if (reimbs == null || reimbs.size() == 0) {
				params1.addProperty("status", "no record");
				String json = gson.toJson(params1);
				pw.write(json);
			} else {

				JsonArray jobj = new JsonArray();
				for (Reimbursement reimb : reimbs) {
					JsonObject params = new JsonObject();
					params.addProperty("reimbId", reimb.getReimbId());
					params.addProperty("reimbAmount", reimb.getReimbAmount());
					params.addProperty("reimbStatusId", reimb.getReimbStatus().getStatusId());

					jobj.add(params);
				}

				pw.write(jobj.toString());
			}
		} catch (Exception e) {
			params1.addProperty("status", "process failed");
			String json = gson.toJson(params1);
			pw.write(json);
		}

	}
	

}
