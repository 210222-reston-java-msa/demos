package com.revature.models;

/**
 * 
 * Type of reimbursement:
 * 1. Lodging
 * 2. Travel
 * 3. Housing
 * 4. Other
 */
public class RType {
	
	private int id;
	private String type;
	
	public RType() {}
	
	public RType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "RType [id=" + id + ", type=" + type + "]";
	}
	
	
	

}
