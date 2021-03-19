package com.revature.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReimbType {

	@Id
	private int typeId;
	private String type;
	
	public ReimbType() {
		// TODO Auto-generated constructor stub
	}
		
	public ReimbType(int typeId) {
		super();
		this.typeId = typeId;
	}

	public ReimbType(String type) {
		super();
		this.type = type;
	}


	public ReimbType(int typeId, String type) {
		super();
		this.typeId = typeId;
		this.type = type;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "ReimbType [typeId=" + typeId + ", type=" + type + "]";
	}
	
}
