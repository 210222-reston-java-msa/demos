package com.revature.repositories;

import com.revature.models.User;

public interface UsersDAO {

	public User getUserByUserName (String userName);
	
	public boolean updateUser (User user);
	
	public boolean userExists (String userName);
	
	public boolean createUser (User user);
}
