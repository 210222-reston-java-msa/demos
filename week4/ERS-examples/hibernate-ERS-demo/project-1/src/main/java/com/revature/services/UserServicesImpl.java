package com.revature.services;

import java.util.List;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.models.User;

public class UserServicesImpl implements UserServices{

	private static UserDao userDao = new UserDaoImpl();
	
	@Override
	public String addUser(User user) {
		
		if(!userDao.isAvailableUsername(user.getUsername())) {
			return "username is already used";
		}
		
		if(!userDao.isAvailableEmail(user.getEmail())) {
			return "email is already used";
		}
		
		if(userDao.addUser(user)) {
			return "success";
		} else {
			return "process failed";
		}
	}

	@Override
	public List<User> getAllUsers() {
		
		return userDao.getAllUsers();
	
	}

	@Override
	public User getUser(String username, String password) {
		
		return userDao.getUser(username, password);
	
	}
	
	@Override
	public User getUser(int userId) {

		return userDao.getUser(userId);
	}
	

	@Override
	public String getRole(int userId) {
		
		User user = userDao.getUser(userId);
		
		return user.getRole().getRole();
		
	}

	@Override
	public String updateUser(User newUser, int userId) {
		
		User user = userDao.getUser(userId);
		newUser.setRole(user.getRole());
		newUser.setUserId(user.getUserId());
		
		System.out.println(user);
		System.out.println(newUser);
		
		if(!userDao.isAvailableUsername(newUser.getUsername())) {
			if(!user.getUsername().equals(newUser.getUsername())){
				return "username is already used";
			}
		}
		
		if(!userDao.isAvailableEmail(newUser.getEmail())) {
			if(!user.getEmail().equals(newUser.getEmail())){
				return "email is already used";
			}
		}
				
		if(userDao.updateUser(newUser)) {
			return "success";
		} else {
			return "process failed";
		}		
	}

	@Override
	public String getEmail(int reimbId) {
		return userDao.getEmail(reimbId);
	}

	@Override
	public User getUser(String email) {
		return userDao.getUser(email);
	}




	
	

}
