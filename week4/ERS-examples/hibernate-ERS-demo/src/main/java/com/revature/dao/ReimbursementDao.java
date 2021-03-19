package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao {

	public boolean addReimbursement(Reimbursement reimb);
	
	public Reimbursement getReimb(int reimbId);
	public List<Reimbursement> getAllReimbursements();
	public List<Reimbursement> getAllReimbursements(int userId);
	public List<Reimbursement> getAllReimbursementsForChart();
	public List<Reimbursement> getAllReimbursementsForChart(int userId);
	public List<Reimbursement> getPendingReimbs(int statusId); // Manager
	public List<Reimbursement> getPendingReimbs(int userId, int statusId); //Employee 
	public List<Reimbursement> getResolvedReimbs(int statusOneId, int statusTwoId);  //Manager
	public List<Reimbursement> getResolvedReimbs(int userId, int statusOneId, int statusTwoId); //Employee 


	
	public boolean approveReimb(int reimbId, int userId);
	public boolean denyReimb(int reimbId, int userId);
	
	public Reimbursement getReceipt(int reimbId);
	
}
