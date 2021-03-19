package com.revature.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.DAO.UserDao;
import com.revature.models.User;
 
public class UserService {
	
	private static Logger logger = LogManager.getLogger(UserService.class);
	static UserDao ud = new UserDao();
	
	public User getUserById(int id) {
		User u = ud.findById(id);
		return u;
	}
	
	/**
	 * 
	 * @param email
	 * @return User from DB. Null if no users with this email exist
	 */
	public User getUserByEmail(String email) {
		if (email == null) return null;
		User u = ud.findByEmail(email);
		return u;
	}
	
	/**
	 * @param email
	 * @param password
	 * @return User for corresponding email/password combo. Null if correct combination does not exist in DB.
	 */
	public User getUserByEmailPassword(String email, String password) {
		// Handle null inputs here or elsewhere?
		if (email == null || password == null) return null;
		User u = ud.findByEmailPassword(email, password);
		return u;
	}
	
	/**
	 * @return list of all emails. Null if none exist in DB
	 */
	public List<String> getAllEmails(){
		List<String> emails = new ArrayList<String>();
		emails = ud.findAllEmails();
		return emails;
	}
	
	public List<User> getAllUsers(){
		List<User> users = ud.findAll();
		return users;
	}
	
	public List<User> safeGetAllUsers(){
		List<User> users = ud.safeFindAll();
		return users;
	}
	
	public List<User> getAllUsersExcludeCurrent(int current){
		List<User> users = this.safeGetAllUsers();						// Will send back users with password = null
		User u = null;
		Iterator<User> itr = users.iterator();
		while (itr.hasNext()) {
			u = itr.next();
			if (u.getUserId() == current || u.getApproved() == 0) {		// Remove current manager and unapproved employees
				itr.remove();
			}	
		}
		return users;
	}
		
	public List<User> getAllPendingUsers(){
		User u = null;
		List<User> users = new ArrayList<User>();
		List<User> all = this.getAllUsers();
		Iterator<User> itr = all.iterator();
		while (itr.hasNext()) {
			u = itr.next();
			if (u.getApproved() == 0) {
				users.add(u);
			}	
		}
		return users;
	}

	public User update(User u) {
		logger.debug(u);
		ud.update(u);
		return u;
	}

	/**
	 * Returns null if email already exists
	 */
	public User create(User u) {
		// Validate User
		List<String> emails = ud.findAllEmails();
		if (emails.contains(u.getEmail())) {
			return null;
		}
		else {
			ud.create(u);
			return u;
		}
	}
		
	public void delete(int id) {
		ud.delete(id);
	}
}
