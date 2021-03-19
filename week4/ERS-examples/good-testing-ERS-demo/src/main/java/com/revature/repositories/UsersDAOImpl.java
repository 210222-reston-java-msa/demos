package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;
import com.revature.web.LoginServlet;

public class UsersDAOImpl implements UsersDAO {

	private static final Logger logger = LogManager.getLogger(UsersDAOImpl.class);
	
	public User getUserByUserName(String userName) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			// prepared statement
			String sql = "SELECT * FROM project1.users WHERE userName = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int dbUserId = rs.getInt("userId");
				String dbUserName = rs.getString("userName");
				String dbUserPassword = rs.getString("userPass");
				String dbFirstName = rs.getString("firstName");
				String dbLastName = rs.getString("lastName");
				String dbEmail = rs.getString("email");
				int dbUserRole = rs.getInt("userRole");

				User user = new User(dbUserId, dbUserName, dbUserPassword, dbFirstName, dbLastName, dbEmail, dbUserRole);
				rs.close();
				return user;
			}
			rs.close();
		} catch (SQLException e) {
			logger.warn("Unable to get User from database", e);
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateUser(User user) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "UPDATE project1.users SET userName = ?, userPass = ?, firstName = ?, lastName = ?, email = ?, userRole = ? WHERE userId = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getUserPassword());
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.setString(5, user.getEmail());
			stmt.setInt(6, user.getRoleNum());
			stmt.setInt(7, user.getUserId());
			
			boolean check = stmt.execute();
			if(check == false) {
				return true;
			}

		} catch (SQLException e) {
			logger.warn("Unable to update user information", e);
			e.printStackTrace();
		}
		return false;
	}

	public boolean userExists(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean createUser(User user) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT into project1.users (userName, userPass, firstName, lastName, email, userRole) " +
						"VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getUserPassword());
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.setString(5, user.getEmail());
			stmt.setInt(6, user.getRoleNum());
			
			
			boolean check = stmt.execute();
			if(check == false) {
				return true;
			}

		} catch (SQLException e) {
			logger.warn("Unable to create user in databse", e);
			e.printStackTrace();
		}
		return false;
	}

}