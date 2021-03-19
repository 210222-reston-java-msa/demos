package com.revature.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserRole {

	@Id
	private int roleId;
	private String role;
	
	public UserRole() {
		// TODO Auto-generated constructor stub
	}
		
	public UserRole(String role) {
		super();
		this.role = role;
	}


	public UserRole(int roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRole [roleId=" + roleId + ", role=" + role + "]";
	}
	
}
