package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.NewUserDTO;
import com.revature.models.ReimbursementInput;
import com.revature.models.User;
import com.revature.services.UserLogic;

public class NewUserServlet extends HttpServlet {

	private static final long serialVersionUID = 2779817756052605363L;
	private static final Logger logger = LogManager.getLogger(NewUserServlet.class);

	UserLogic uLogic = new UserLogic();
	private static ObjectMapper om = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			doPost(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();

		BufferedReader reader = req.getReader();

		StringBuilder jsonInput = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			jsonInput.append(line);
			line = reader.readLine();
		}
		String jsonInputString = jsonInput.toString();
		NewUserDTO newUserInput = om.readValue(jsonInputString, NewUserDTO.class);
		if (uLogic.userExists(newUserInput.getUserName())) {
			res.setStatus(204);
		} else {
			User user = new User(newUserInput);
			String password = DigestUtils.sha256Hex(user.getUserPassword());
			user.setUserPassword(password);
			logger.info("User created:[Username: " + user.getUserName() + ", Firstname: " + user.getFirstName()
					+ ", Lastname: " + user.getLastName() + ", User role#: " + user.getRoleNum() + ", Email: "
					+ user.getEmail());
			if (uLogic.createNewUser(user)) {
				res.setStatus(201);
				// maybe redirect back to same page to reset form??
			} else {
				res.setStatus(202);
			}
		}
	}

}
