package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

// This is wehre we write some statments!
public class EmployeeDAOImpl implements EmployeeDAO {

	private static Logger log = Logger.getLogger(EmployeeDAOImpl.class);

	public boolean insert(Employee e) {

		PreparedStatement stmt = null;

		try {

			Connection conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO employee (first_name, last_name, username, pass_word) VALUES (?, ?, ?, ?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, e.getFirstName());
			stmt.setString(2, e.getLastName());
			stmt.setString(3, e.getUsername());
			stmt.setString(4, e.getPassword());

			if (!stmt.execute()) {
				return false;
			}

		} catch (SQLException ex) {
			log.warn("Unable to insert user", ex);
			return false;
		}
		// If everything is successful, we return true
		return true;
	}

	public boolean update(Employee e) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Employee> findAll() {

		List<Employee> list = new ArrayList<Employee>();

		try {
			
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "SELECT * FROM employee";
			
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("pass_word");
				
				Employee e = new Employee(id, first_name, last_name, username, password);
				list.add(e);
			}

		} catch (SQLException ex) {
			log.warn("Unable to retrieve all users", ex);

		}

		return list;
	}

}
