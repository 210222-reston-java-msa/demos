package com.revature.models;

public class DataTransfer {
	
	private int rId;
	private String author;
	private String rResolver;
	private double amount;
	private String submitDate;
	private String resolveDate;
	private String rType;
	private String rDesc;
	private String rStatus;
	
	public DataTransfer() {}

	public DataTransfer(int rId, String author, String rResolver, double amount, String submitDate, String resolveDate,
			String rType, String rDesc, String rStatus) {
		super();
		this.rId = rId;
		this.author = author;
		this.rResolver = rResolver;
		this.amount = amount;
		this.submitDate = submitDate;
		this.resolveDate = resolveDate;
		this.rType = rType;
		this.rDesc = rDesc;
		this.rStatus = rStatus;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getrResolver() {
		return rResolver;
	}

	public void setrResolver(String rResolver) {
		this.rResolver = rResolver;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getResolveDate() {
		return resolveDate;
	}

	public void setResolveDate(String resolveDate) {
		this.resolveDate = resolveDate;
	}

	public String getrType() {
		return rType;
	}

	public void setrType(String rType) {
		this.rType = rType;
	}

	public String getrDesc() {
		return rDesc;
	}

	public void setrDesc(String rDesc) {
		this.rDesc = rDesc;
	}

	public String getrStatus() {
		return rStatus;
	}

	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}

	@Override
	public String toString() {
		return "DataTransfer [rId=" + rId + ", author=" + author + ", rResolver=" + rResolver + ", amount=" + amount
				+ ", submitDate=" + submitDate + ", resolveDate=" + resolveDate + ", rType=" + rType + ", rDesc="
				+ rDesc + ", rStatus=" + rStatus + "]";
	}
	
	

}
