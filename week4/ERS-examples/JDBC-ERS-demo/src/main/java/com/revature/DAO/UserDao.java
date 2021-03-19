package com.revature.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class UserDao {

	/**
	 * @return List of all users in DB. Null if there are no users in the DB
	 */
	public List<User> findAll(){
		List<User> users = new ArrayList<User>();
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM USR ORDER BY LASTNAME";
			Statement statement = conn.createStatement();	// Statement... Potential for SQL injection
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt(1));
				u.setEmail(rs.getString(2));
				u.setPwd(rs.getString(3));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				u.setRoll(rs.getInt(6));
				u.setApproved(rs.getInt(7));
				users.add(u);
			}					
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return users;	// If null, handle in service layer
	}

	/**
	 * @return List of all users, keeping all passwords null for security. Null if there are no users in the DB
	 */
	public List<User> safeFindAll(){
		List<User> users = new ArrayList<User>();
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM USR ORDER BY LASTNAME";
			Statement statement = conn.createStatement();	// Statement... Potential for SQL injection
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt(1));
				u.setEmail(rs.getString(2));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				u.setRoll(rs.getInt(6));
				u.setApproved(rs.getInt(7));
				users.add(u);
			}					
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return users;	// If null, handle in service layer
	}
	
	
	public User findById(int id) {
		User u = null;
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM USR WHERE USER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);		// Prepared Statement
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User();
				u.setUserId(rs.getInt(1));
				u.setEmail(rs.getString(2));
				u.setPwd(rs.getString(3));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				u.setRoll(rs.getInt(6));
				u.setApproved(rs.getInt(7));
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return u;	// Returns null if email does not exist in DB		
	}
	
	/**
	 * 
	 * @param email: unique username for users
	 * @return User corresponding to email address. Null if email address doesn't exist in DB
	 */
	public User findByEmail(String email) {
		User u = null;
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM USR WHERE EMAIL = ?";
			PreparedStatement ps = conn.prepareStatement(sql);		// Prepared Statement
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User();
				u.setUserId(rs.getInt(1));
				u.setEmail(rs.getString(2));
				u.setPwd(rs.getString(3));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				u.setRoll(rs.getInt(6));
				u.setApproved(rs.getInt(7));
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return u;	// Returns null if email does not exist in DB
	}
	
	/**
	 * 
	 * @param email
	 * @param password
	 * @return User for corresponding email/password combo. Null if correct combination does not exist in DB.
	 */
	public User findByEmailPassword(String email, String password) { //password1
//		int pass = password1.hashCode();
//		String password = new Integer(pass).toString();
		User u = null;
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM USR WHERE EMAIL = ? AND PWD = ?";
			System.out.println(conn);
			PreparedStatement ps = conn.prepareStatement(sql);		// Prepared Statement
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User();
				u.setUserId(rs.getInt(1));
				u.setEmail(rs.getString(2));
				u.setPwd(rs.getString(3));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				u.setRoll(rs.getInt(6));
				u.setApproved(rs.getInt(7));
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;	
	}
	
	/**
	 * @return List of all emails in the DB. Null if none exist
	 */
	public List<String> findAllEmails(){
		List<String> emails = new ArrayList<String>();
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "{CALL GET_USR_EMAILS(?)}";
			CallableStatement cs = conn.prepareCall(sql);			// Callable Statement
			cs.registerOutParameter(1, Types.REF_CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(1);
			while(rs.next()) {
				emails.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return emails;
	}
	
	/**
	 * @param User u
	 * @return the created user with updated UserId. Null if user was not created in the DB
	 */
	public User create(User u) {
//		String pass = u.getPwd();
//		int pass1 = pass.hashCode();
//		String password = new Integer(pass1).toString();
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "INSERT INTO USR(EMAIL, PWD, FIRSTNAME, LASTNAME, ROLL, APPROVED) VALUES(?,?,?,?,?,?)";
			//String[] keyNames = {"USER_ID"};
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getEmail());
			ps.setString(2, u.getPwd()); // password 
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setInt(5, u.getRoll());
			ps.setInt(6, u.getApproved());
			int numRows = ps.executeUpdate();
			if (numRows == 0) {
				return null;
			}		
			else {
				ResultSet pk = ps.getGeneratedKeys();	// primary keys
				while (pk.next()) {
					u.setUserId(pk.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;		
	}
	
	/**
	 * @param User u
	 * @return User if update was successful. Null if not. 
	 */
	public User update(User u) {
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "UPDATE USR SET EMAIL = ?, PWD = ?, FIRSTNAME = ?, LASTNAME = ?, ROLL = ?, APPROVED = ? WHERE USER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getEmail());
			ps.setString(2, u.getPwd());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setInt(5, u.getRoll());
			ps.setInt(6, u.getApproved());
			ps.setInt(7, u.getUserId());
			int numRows = ps.executeUpdate();
			if (numRows == 0) {
				return null;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return u;
	}

	/**
	 * ONLY DELETES WHEN MANAGER DENIES USER
	 * @param id
	 */
	public void delete(int id) {
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "DELETE FROM USR WHERE USER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
