package com.revature.models;

public class ReimbursementInput {

	private double amount;
	
	private String description;
	
	//private byte[] receipt;
	
	//private int author;
	
	private int typeNum;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public byte[] getReceipt() {
//		return receipt;
//	}
//
//	public void setReceipt(byte[] receipt) {
//		this.receipt = receipt;
//	}

//	public int getAuthor() {
//		return author;
//	}
//
//	public void setAuthor(int author) {
//		this.author = author;
//	}

	public int getTypeNum() {
		return typeNum;
	}

	public void setTypeNum(int typeNum) {
		this.typeNum = typeNum;
	}

	public ReimbursementInput() {
		
	}
	
	public ReimbursementInput(double amount, String description, int typeNum) {
		super();
		this.amount = amount;
		this.description = description;
		this.typeNum = typeNum;
//		this.receipt = receipt;
		
		
	}
	
	
	
	
}
