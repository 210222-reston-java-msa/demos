package com.revature.services;

import java.util.List;

import com.revature.models.User;

public interface UserServices {
	
	public String addUser(User user);	
	
	public List<User> getAllUsers();
	public User getUser(String username, String password);
	public User getUser(int userId);
	public User getUser(String email);
	public String getRole(int userId);

	public String updateUser(User user, int userId);
	
	public String getEmail(int reimbId);
	
	
	
}
