package com.revature.util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.revature.models.Reimbursement;
import com.revature.models.User;

public class SendEmail {

	private final static String username = "isa_delibas";
	private final static String password = "nzxcyoygwhapsxxn";
	private final static String fromEmail = "isa_delibas@yahoo.com";
	
	public static boolean sendEmail(Reimbursement reimb, String toEmail, String status) {

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.mail.yahoo.com");
		properties.put("mail.smtp.port", "587");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromEmail));

			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

			if (status.equals("Approved")) {
				
				String html = "<html>\r\n" + "<head>\r\n" + "<style>\r\n" + "table, th, td {\r\n"
						+ "  border: 2px solid green;\r\n" + "}\r\n" + "</style>\r\n" + "</head>\r\n" + "<body>\r\n"
						+ "\r\n" + "<h2>Reimbursement Report</h2>\r\n" + "\r\n" + "<table style=\"width:400px\">\r\n"
						+ "\r\n" + "  <tr>\r\n" + "    <td>Status</td>\r\n" + "    <td>" + status + "</td>\r\n"
						+ "  </tr>\r\n" + "  <tr>\r\n" + "    <td>Reimbursement Id</td>\r\n" + "    <td>"
						+ reimb.getReimbId() + "</td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n" + "  <tr>\r\n"
						+ "    <td>Cost</td>\r\n" + "    <td>" + reimb.getReimbAmount() + "</td>\r\n" + "  </tr>\r\n"
						+ "  <tr>\r\n" + "    <td>Description</td>\r\n" + "    <td>" + reimb.getReimbDescription()
						+ "</td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n" + "    <td>Type</td>\r\n" + "    <td>"
						+ reimb.getReimbType().getType() + "</td>\r\n" + "  </tr>\r\n" + "</table>\r\n" + "\r\n"
						+ "</body>\r\n" + "</html>";
				
				
				msg.setSubject("Your reimbursement request approved!");
				msg.setText("Hello Isa");
				msg.setContent(html, "text/html;charset=UTF-8");
				Transport.send(msg);
			} else {
				
				String html = "<html>\r\n" + "<head>\r\n" + "<style>\r\n" + "table, th, td {\r\n"
						+ "  border: 2px solid red;\r\n" + "}\r\n" + "</style>\r\n" + "</head>\r\n" + "<body>\r\n"
						+ "\r\n" + "<h2>Reimbursement Report</h2>\r\n" + "\r\n" + "<table style=\"width:400px\">\r\n"
						+ "\r\n" + "  <tr>\r\n" + "    <td>Status</td>\r\n" + "    <td>" + status + "</td>\r\n"
						+ "  </tr>\r\n" + "  <tr>\r\n" + "    <td>Reimbursement Id</td>\r\n" + "    <td>"
						+ reimb.getReimbId() + "</td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n" + "  <tr>\r\n"
						+ "    <td>Cost</td>\r\n" + "    <td>" + reimb.getReimbAmount() + "</td>\r\n" + "  </tr>\r\n"
						+ "  <tr>\r\n" + "    <td>Description</td>\r\n" + "    <td>" + reimb.getReimbDescription()
						+ "</td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n" + "    <td>Type</td>\r\n" + "    <td>"
						+ reimb.getReimbType().getType() + "</td>\r\n" + "  </tr>\r\n" + "</table>\r\n" + "\r\n"
						+ "</body>\r\n" + "</html>";
				
				msg.setSubject("Your reimbursement request denied!");
				msg.setContent(html, "text/html;charset=UTF-8");
				Transport.send(msg);
			}

			
			System.out.println("Email sent successfully");
			return true;
		} catch (MessagingException e) {
			System.out.println(e);
			System.out.println("Email not send");
		}

		return false;
	}

	public static boolean sendEmail(User user) {

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.mail.yahoo.com");
		properties.put("mail.smtp.port", "587");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromEmail));

			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));

			msg.setSubject("Your Password Information");
			
			String body = "Hi, " + user.getFirstName() + " " + user.getLastName() + "\n\n"
						 + "Your username: " + user.getUsername() + "\n" 
						 + "Your password: " + user.getPassword() + "\n\n"
						 + "Have a good one\nBest.\n\n ERS Team";
	
			
			msg.setText(body);
			Transport.send(msg);			
			System.out.println("Email sent successfully");
			return true;
		} catch (MessagingException e) {
			System.out.println("Email not send");
		}

		return false;
	}
	
	
	public static boolean sendEmail(User user, String subject, String description, String type) {

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.mail.yahoo.com");
		properties.put("mail.smtp.port", "587");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromEmail));

			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("isa_delibas@yahoo.com"));


			msg.setSubject("Your Feedback");
			
			String html = "<html>\r\n" + "<head>\r\n" + "<style>\r\n" + "table, th, td {\r\n"
					+ "  border: 2px solid blue;\r\n" + "}\r\n" + "</style>\r\n" + "</head>\r\n" + "<body>\r\n"
					+ "\r\n" + "<h2>Feedback</h2>\r\n" + "\r\n" + "<table style=\"width:400px\">\r\n"
					+ "\r\n" + "  <tr>\r\n" + "    <td>Feedback Owner</td>\r\n" + "    <td>" + user.getFirstName() + " " + user.getLastName() + "</td>\r\n"
					+ "  </tr>\r\n" + "  <tr>\r\n" + "    <td>Subject Id</td>\r\n" + "    <td>"
					+ subject + "</td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n" + "  <tr>\r\n"
					+ "    <td>Category</td>\r\n" + "    <td>" + type + "</td>\r\n" + "  </tr>\r\n"
					+ "  <tr>\r\n" + "    <td>Description</td>\r\n" + "    <td>" + description
					+ "</td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n" + "    <td>Type</td>\r\n" + "    <td>"
					+ user.getEmail() + "</td>\r\n" + "  </tr>\r\n" + "</table>\r\n" + "\r\n"
					+ "</body>\r\n" + "</html>";
			
			msg.setSubject("Your feedback");
			msg.setContent(html, "text/html;charset=UTF-8");
			Transport.send(msg);
			
			System.out.println("Email sent successfully");
			return true;
		} catch (MessagingException e) {
			System.out.println("Email not send");
		}

		return false;
	}
	

}
