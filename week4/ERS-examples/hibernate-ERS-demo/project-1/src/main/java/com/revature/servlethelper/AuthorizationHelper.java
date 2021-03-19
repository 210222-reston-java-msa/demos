package com.revature.servlethelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.models.User;
import com.revature.services.UserServices;
import com.revature.services.UserServicesImpl;
import com.revature.util.SendEmail;

public class AuthorizationHelper {

	private static UserServices userService = new UserServicesImpl();

	public static void signin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();

		try {

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject jsonobj = root.getAsJsonObject();

			String username = jsonobj.get("username").getAsString();
			String password = jsonobj.get("password").getAsString();

			User user = userService.getUser(username, password);

			if (user != null) {

				HttpSession session = req.getSession();
				session.setAttribute("userId", user.getUserId());			
				String json = gson.toJson(user);			

				pw = resp.getWriter();
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

	public static void signout(HttpServletRequest req, HttpServletResponse resp) throws IOException {

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
			
			if (session.getAttribute("userId") == null || !req.getMethod().equals("POST")) {

				params.addProperty("status", "process failed");
				String json = gson.toJson(params);
				pw.write(json);
				
			} else {

				session.removeAttribute("userId");
				session.invalidate();
				
				params.addProperty("status", "success");
				String json = gson.toJson(params);
				pw.write(json);
			}

		} catch (Exception e) {
			params.addProperty("status", "process failed");
			String json = gson.toJson(params);
			pw.write(json);
		}

	}
	
	public static void forgotPass(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();

		try {

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();

			String toEmail = rootobj.get("email").getAsString();
			
			User user = userService.getUser(toEmail);
			

			if (user != null) {

				SendEmail email = new SendEmail();
				email.sendEmail(user);
				String emailStatus = "Email Success";

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

}
