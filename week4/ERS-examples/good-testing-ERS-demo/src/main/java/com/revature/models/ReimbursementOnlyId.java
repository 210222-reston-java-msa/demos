package com.revature.models;

public class ReimbursementOnlyId {

	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ReimbursementOnlyId(int id) {
		super();
		this.id = id;
	}

	public ReimbursementOnlyId() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ReimbursementOnlyId [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		ReimbursementOnlyId other = (ReimbursementOnlyId) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
