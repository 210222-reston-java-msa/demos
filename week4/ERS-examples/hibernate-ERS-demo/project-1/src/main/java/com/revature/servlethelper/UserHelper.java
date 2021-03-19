package com.revature.servlethelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.services.UserServices;
import com.revature.services.UserServicesImpl;
import com.revature.util.SendEmail;

public class UserHelper {

	private static UserServices userService = new UserServicesImpl();

	public static void signup(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();

		try {
			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));

			JsonObject rootobj = root.getAsJsonObject();
			String username = rootobj.get("username").getAsString();
			String password = rootobj.get("password").getAsString();
			String confirmPassword = rootobj.get("confirmPassword").getAsString();
			String firstName = rootobj.get("firstName").getAsString();
			String lastName = rootobj.get("lastName").getAsString();
			String email = rootobj.get("email").getAsString();

			if (!password.equals(confirmPassword)) {
				params.addProperty("status", "password not match");
				String json = gson.toJson(params);
				pw.write(json);
				return;
			}

			User user = new User(username, password, firstName, lastName, email, new UserRole(2, "Employee"));	
		
			String message = userService.addUser(user);
			System.out.println(message);

			params.addProperty("status", message);
			String json = gson.toJson(params);
			pw.write(json);

		} catch (Exception e) {
			params.addProperty("status", "process failed");
			String json = gson.toJson(params);
			pw.write(json);
		}

	}

	public static void getUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();

		try {

			HttpSession session = req.getSession();
			int userId = (int) session.getAttribute("userId");

			User user = userService.getUser(userId);
			
			if(user == null) {
				params.addProperty("status", "no record");
				String json = gson.toJson(params);
				pw.write(json);
			} else {
				String json = gson.toJson(user);
				pw.write(json);
			}

		} catch (Exception e) {
			params.addProperty("status", "process failed");
			String json = gson.toJson(params);
			pw.write(json);
		}

	}

	public static void getAllUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();

		try {

			List<User> users = userService.getAllUsers();
			
			if(users == null || users.size() == 0) {
				params.addProperty("status", "no record");
				String json = gson.toJson(params);
				pw.write(json);
			} else {
				String json = gson.toJson(users);
				pw.write(json);
			}

		} catch (Exception e) {
			params.addProperty("status", "process failed");
			String json = gson.toJson(params);
			pw.write(json);
		}

	}

	public static void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

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

			String username = rootobj.get("username").getAsString();
			String password = rootobj.get("password").getAsString();
			String confirmPassword = rootobj.get("confirmPassword").getAsString();
			String firstName = rootobj.get("firstName").getAsString();
			String lastName = rootobj.get("lastName").getAsString();
			String email = rootobj.get("email").getAsString();
			
			if (!password.equals(confirmPassword)) {
				params.addProperty("status", "password not match");
				String json = gson.toJson(params);
				pw.write(json);
				return;
			}
			
			User user = new User(username, password, firstName, lastName, email);
			
			String message = userService.updateUser(user, userId);
			params.addProperty("status", message);
			String json = gson.toJson(params);
			pw.write(json);

		} catch (Exception e) {
			params.addProperty("status", "process failed");
			String json = gson.toJson(params);
			pw.write(json);
		}
	}
	
	
	@SuppressWarnings("static-access")
	public static void feedback(HttpServletRequest req, HttpServletResponse resp) throws IOException {

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

			String subject = rootobj.get("subject").getAsString();
			String description = rootobj.get("description").getAsString();
			int typeId = rootobj.get("typeId").getAsInt();
			String type = "";
			if(typeId == 1) {
				type = "Suggestion";
			} else if(typeId == 2) {
				type = "Question";
			} else {
				type = "Complaint";
			}
			
			System.out.println(subject);
			System.out.println(description);
			
			
			User user = userService.getUser(userId);			
			SendEmail email = new SendEmail();
			
			if(email.sendEmail(user, subject, description, type)) {
				
				String emailStatus = "Email Success";
				params.addProperty("status", emailStatus);
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

}
