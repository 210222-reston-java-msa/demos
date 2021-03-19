package com.revature.repositories;

import java.util.ArrayList;
import java.util.TreeMap;

import com.revature.models.Reimbursement;

public interface ReimbursementsDAO {

	//public TreeMap<Integer, Reimbursement> getReimbursementsFromUsername (String userName);
	
	public TreeMap<Integer, Reimbursement> getReimbursementsFromUserId (int userId);
	
	public TreeMap<Integer, Reimbursement> getReimbursementsFromStatus (int statusNum);
	
	public TreeMap<Integer, Reimbursement> getAllReimbursements();
	
	public Reimbursement getReimbursementFromReimId (int id);
	
	public boolean updateReimbursement (Reimbursement reimbursement);
	
	public boolean updateReimbursementStatus (Reimbursement reimbursement);
	
	public boolean createReimbursement (Reimbursement reimbursement);
}
