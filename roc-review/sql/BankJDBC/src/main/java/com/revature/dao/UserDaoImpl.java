package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDaoImpl implements UserDao {
/**
 * This is a class that follows the DAO Design Pattern.
 * 
 * DAO stands for Data Access Object, and it is responsible for handling
 * all logic that will be used to interact with the Database.
 * 
 * That way, other parts of our application can interact with the database without
 * needing to think about this complex logic.
 * 
 * We can simply use the methods available on the interface.
 */
	@Override
	public int insert(User u) {

		try {
			// Step 1. Get a connection using ConnectionUtil
			// import Connection from java.sql
			Connection conn = ConnectionUtil.getConnection();

			// Step 2. Define our SQL statements to perform an action on our DB
			String columns = "username, pass, first_name, last_name, email, role_id";
			String sql = "INSERT INTO users (" + columns + ") VALUES (?, ?, ?, ?, ?, ?)";
			// The ?'s are place-holders for input values
			// They work for PreparedStatements, which are designed to protect us from SQL
			// injection

			// Step 3a: Obtain Statement Object
			// PreparedStatement is a sub interface of Statement that provides extra
			// security to prevent SQL injection.
			PreparedStatement stmt = conn.prepareStatement(sql);

			// Step 3b: Inject values to replace all the ? marks
			stmt.setString(1, u.getUsername()); // replace 1st ? mark with u.getUsername()
			stmt.setString(2, u.getPassword()); // replace 2nd ? mark with u.getPassword()
			stmt.setString(3, u.getFirstName()); // replace 3rd ? mark with u.getFirstName()
			stmt.setString(4, u.getLastName());
			stmt.setString(5, u.getEmail());
			stmt.setInt(6, u.getRole().getId()); // this is returning the int value for the Role id of the Role object

			// Step 4: Execute the statement;
			return stmt.executeUpdate(); // this will return the number of rows inserted (1)

		} catch (SQLException e) {
			// Step 5: Perform any exception handling in an appropriate means
			e.printStackTrace();
		}

		// otherwise we return 0 if we cannot insert a row into the database
		return 0;
	}

	// this method will pull all records from our DB and store them in Objects in
	// our Java applciation
	@Override
	public List<User> findAll() {
		// In this method, we are planning on returning ALL User objects
		// So we prepare the List<User> at the top, which we will fill with all the Users we collect
		List<User> allUsers = new ArrayList<User>();

		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM users INNER JOIN roles ON users.role_id = roles.id";
		// This is returning BOTH user info AND the name of their Role

		try {
			Statement stmt = conn.createStatement();

			// Special step for retrieving objects FROM the database:
			// we read the values of each column by using a special 
			// iterator called a ResultSet.

			// Steps 1 - 3 are similar to our first method
			// We must make sure to use this ResultSet to save our data to the List that was prepared at the top ^.
			// The ResultSet interface represents all of the data obtained from our query.
			// It has data for every column that we obtained from our query, per record
			ResultSet rs = stmt.executeQuery(sql);

			// ResultSets are similar to iterators, so this while-loop will
			// iterate over every record that we obtian from the database;
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username"); // Every word in " " is the name of the SQL column we're iterating over
				String pass = rs.getString("pass");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				int roleId = rs.getInt("role_id");
				String roleName = rs.getString("role_name");

				// No we use the data we just pulled to create a User object
				Role r = new Role(roleId, roleName);
				User u = new User(id, username, pass, firstName, lastName, email, r);

				// Now make sure to add it to our list of Users
				allUsers.add(u);
			}

		} catch (SQLException e) {

			e.printStackTrace();
			// if something goes wrong, return an empty list
			return new ArrayList<User>();
			
		}

		// If everything goes well, return the allUsers arrayList
		return allUsers;
	}

	@Override
	public User findById(int id) {
		// In this method we return ALL user objects

		// Hence, we need to create a User at the top of the method.  We will give this User it's properties
		User u = new User();

		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM users INNER JOIN roles ON users.role_id = roles.id WHERE users.id = " + id + "";
		// This is returning BOTh user info AND the name of their Role

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("pass"));
				u.setFirstName(rs.getString("first_name"));
				u.setLastName(rs.getString("last_name"));
				u.setEmail(rs.getString("email"));
				u.setRole(new Role(rs.getInt("role_id"), rs.getString("role_name")));
			}

		} catch (SQLException e) {

			e.printStackTrace();
			// if something goes wrong, return null
			return null;
		}

		// If everything goes well, return the User object
		return u;
	}

	@Override
	public User findByUsername(String username) {

				// Hence, we need to create a User at the top of the method.  We will give this User it's properties
				User u = new User();

				Connection conn = ConnectionUtil.getConnection();
				
				// this is what I had before...this is what caused the problem! (See lines 176 - 185 for an explanation).
			    //String sql = "SELECT * FROM users INNER JOIN roles ON users.role_id = roles.id WHERE users.username = "+ username +  "";
				
				String sql = "SELECT * FROM users INNER JOIN roles ON users.role_id = roles.id WHERE users.username = ? ";


				try {
					/*
					*Notice that I used a prepared statement for the username passed through the method.
					*
					 Using a prepared statement minimizes complexity. If I had simply concatenated the username (like I had before),
					 SQL would have read the statement with "".  
					 
					 WHat do the extra ""'s do?
					 Instead of looking up the specific username value, it would have 
					 searched for a column or table called "username" BEACAUSE I used ""'s.
					 
					 It is best to use a prepared statement, which I have done below:
					 */
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, username);
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						u.setId(rs.getInt(1));
						u.setUsername(username); // we already passed this in through the method
						u.setPassword(rs.getString(3));
						u.setFirstName(rs.getString(4)); // this denotes the number of the column of data that's returning
						u.setLastName(rs.getString(5));
						u.setEmail(rs.getString(6));

						u.setRole(new Role(rs.getInt("role_id"), rs.getString("role_name"))); // THIS IS FROM THE ROLES table
						// we "stuck" the roles table TO the users table with a joins statement
						// ^ the above line builds a role object ^ 
					}

				} catch (SQLException e) {

					e.printStackTrace();
					// if something goes wrong, return null
					return null;
				}

				// If everything goes well, return the User object
				return u;
	}

	@Override
	public int update(User u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(User u) {
		
		int idToDelete = u.getId();
		
		Connection conn = ConnectionUtil.getConnection();
		String sql = "DELETE FROM users WHERE id = " + idToDelete + "";
		// and then continue the steps.....
		
		// TODO Auto-generated method stub
		return 0;
	}

}
