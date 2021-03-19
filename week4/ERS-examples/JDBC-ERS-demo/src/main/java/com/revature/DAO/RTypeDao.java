package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.RType;
import com.revature.util.ConnectionFactory;

public class RTypeDao {

	public List<RType> findALL() {
		List<RType> types = new ArrayList<RType>();
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM REIMBURSEMENT_TYPE ORDER BY R_TYPE_ID";
			Statement statement = conn.createStatement();	// Statement... Potential for SQL injection
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				RType t = new RType();
				t.setId(rs.getInt(1));
				t.setType(rs.getString(2));
				types.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return types;
	}

	
	// Might not need this...
	public String findTypeById(int id) {
		String type = null;
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT R_TYPE FROM REIMBURSEMENT_TYPE WHERE R_TYPE_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			type = rs.getString(1);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type;
	}

}
