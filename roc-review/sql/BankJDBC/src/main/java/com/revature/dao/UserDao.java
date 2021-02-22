package com.revature.dao;

import java.util.List;

import com.revature.models.User;

/*
 * DAO stands for data access object
 * 
 * The DAO interface defines all the methods that we plan to use to interact with our database
 * 
 * We have methods for all 4 CRUD operations
 * 	- CREATE (add)
 * 	- READ (retrieve)
 * 	- UPDATE
 * 	- DELETE 
 * 
 * We might include a couple extra read operations as well (like return all, and return just 1 user)
 * 
 */
public interface UserDao {
	
	public int insert(User u);					// CREATE
	
	public List<User> findAll();				// READ (findALL)
	public User findById(int id); 				// READ (return 1 by id)
	public User findByUsername(String username);// READ (return 1 by username)
	
	public int update(User u);					// UPDATE
	
	public int delete(User u);					// DELETE	

}
