package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.models.Reimbursement;

public class ReimbursementServicesImpl implements ReimbursementServices{

	private static ReimbursementDao reimbDao = new ReimbursementDaoImpl();
	private static UserServices userService = new UserServicesImpl();
	
	
	@Override
	public boolean addReimbursement(Reimbursement reimb) {
		
		return reimbDao.addReimbursement(reimb);
	}

	@Override
	public List<Reimbursement> getAllReimbursements(int userId) {
		String role = userService.getRole(userId);
		
		if(role.equals("Employee")) {
			return reimbDao.getAllReimbursements(userId);
		} else if(role.equals("Manager")) {
			return reimbDao.getAllReimbursements();
		}
		return null;
	}
	
	@Override
	public List<Reimbursement> getAllReimbursementsForChart(int userId) {
		
		
		String role = userService.getRole(userId);
		
		if(role.equals("Employee")) {
			return reimbDao.getAllReimbursementsForChart(userId);
		} else if(role.equals("Manager")) {
			return reimbDao.getAllReimbursementsForChart();
		}
		return null;
		
	}
	
	@Override
	public List<Reimbursement> getReimbsByStatus(int userId, String status) {
		

		String role = userService.getRole(userId);
		
		if(role.equals("Employee")) {
			
			if(status.equals("Pending")) {
				return reimbDao.getPendingReimbs(userId, 1); 
			} else if(status.endsWith("Resolved")) {
				return reimbDao.getResolvedReimbs(userId, 2, 3);
			}
			
		} else if(role.equals("Manager")) {
			
			if(status.equals("Pending")) {
				return reimbDao.getPendingReimbs(1); 
			} else if(status.equals("Resolved")) {
				return reimbDao.getResolvedReimbs(2, 3);
			}
			
		}
		
		
		return null;
	}

	@Override
	public boolean approveReimb(int reimbId, int userId) {
		return reimbDao.approveReimb(reimbId, userId);
	}

	@Override
	public boolean denyReimb(int reimbId, int userId) {
		return reimbDao.denyReimb(reimbId, userId);
	}

	@Override
	public List<Reimbursement> searchReimb(int employeeId) {
		
//		if(statusId == 1) {
//			return reimbDao.getPendingReimbs(employeeId, statusId);
//		} else if(statusId == 2) {
//			return reimbDao.getResolvedReimbs(employeeId, 2, 3);
//		} else if(statusId == 3) {
			return reimbDao.getAllReimbursements(employeeId);
//		} 
		

	}

	@Override
	public Reimbursement getReceipt(int reimbId) {
		return reimbDao.getReceipt(reimbId);
	}

	@Override
	public Reimbursement getReimb(int reimbId) {
		return reimbDao.getReimb(reimbId);
	}




	
}
