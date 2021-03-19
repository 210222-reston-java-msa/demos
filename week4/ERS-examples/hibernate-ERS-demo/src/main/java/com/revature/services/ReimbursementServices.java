package com.revature.services;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementServices {

	public boolean addReimbursement(Reimbursement reimb);
	
	public List<Reimbursement> getAllReimbursements(int userId);
	public List<Reimbursement> getReimbsByStatus(int userId, String status);
	public List<Reimbursement> getAllReimbursementsForChart(int userId);
	public Reimbursement getReimb(int reimbId);
	
	public boolean approveReimb(int reimbId, int userId);
	public boolean denyReimb(int reimbId, int userId);
	public List<Reimbursement> searchReimb(int employeeId);
	
	public Reimbursement getReceipt(int reimbId);
}
