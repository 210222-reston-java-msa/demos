package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface UserDao {

	public boolean addUser(User user);
	
	public List<User> getAllUsers();
	
	public User getUser(String username, String password);
	public User getUser(int userId);
	public User getUser(String email);
	
	public boolean updateUser(User user);
	
	public String getEmail(int reimbId);
	
	public boolean isAvailableEmail(String email);
	public boolean isAvailableUsername(String username);
	


}
