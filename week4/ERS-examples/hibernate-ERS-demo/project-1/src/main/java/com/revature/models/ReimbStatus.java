package com.revature.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReimbStatus {

	@Id
	private int statusId;
	private String status;
	
	public ReimbStatus() {
		// TODO Auto-generated constructor stub
	}

	
	public ReimbStatus(String status) {
		super();
		this.status = status;
	}


	public ReimbStatus(int statusId) {
		super();
		this.statusId = statusId;
	}


	public ReimbStatus(int statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ReimbStatus [statusId=" + statusId + ", status=" + status + "]";
	}
	
	
}
