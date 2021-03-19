package com.revature.models;

public class ReimbursementStatusDTO {

	private int reimID;
	
	private int statusNum;

	public int getReimID() {
		return reimID;
	}

	public void setReimID(int reimID) {
		this.reimID = reimID;
	}

	public int getStatusNum() {
		return statusNum;
	}

	public void setStatusNum(int statusNum) {
		this.statusNum = statusNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimID;
		result = prime * result + statusNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementStatusDTO other = (ReimbursementStatusDTO) obj;
		if (reimID != other.reimID)
			return false;
		if (statusNum != other.statusNum)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementStatusDTO [reimID=" + reimID + ", statusNum=" + statusNum + "]";
	}
	
	
}
