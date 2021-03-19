package com.revature.services;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.TreeMap;

import com.revature.models.ReimStatus;
import com.revature.models.ReimTypes;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementInput;
import com.revature.repositories.ReimbursementsDAO;
import com.revature.repositories.ReimbursementsDAOImpl;
import com.revature.repositories.UsersDAO;
import com.revature.repositories.UsersDAOImpl;

public class ReimbursementLogic {

	public ReimbursementLogic() {}
	

	public ReimbursementLogic(ReimbursementsDAO rDAO) {
		this.rDAO = rDAO;
	}
	
	UsersDAO uDAO = new UsersDAOImpl();
	ReimbursementsDAO rDAO = new ReimbursementsDAOImpl();
	
	public boolean createNewReimbursement(ReimbursementInput input, int author, byte[] file) {
		Reimbursement newReimbursement = new Reimbursement(input);
		newReimbursement.setAuthor(author);
		newReimbursement.setReceipt(file);
		if(isValidReimbursement(newReimbursement)) {
			newReimbursement.setTimeSubmitted(createSubmissionTime());
		boolean success = rDAO.createReimbursement(newReimbursement);
		return success;
		} else {
			return false;
		}
		
	}
	
	
	public ReimStatus statusToEnum(int statusNum) {
		ReimStatus status;
		if(statusNum == 0) {
			status = ReimStatus.Pending;
			return status;
		} else if(statusNum == 1) {
			status = ReimStatus.Approved;
			return status;
		} else if(statusNum == -1) {
			status = ReimStatus.Denied;
			return status;
		}
		return null;
	}
	
	public int enumToStatus(ReimStatus statusEnum) {
		int status;
		if(statusEnum == ReimStatus.Pending) {
			status = 0;
			return status;
		} else if(statusEnum == ReimStatus.Approved) {
			status = 1;
			return status;
		} else if(statusEnum == ReimStatus.Denied) {
			status = -1;
			return status;
		}
		else return 100;
	}
	
	public ReimTypes typeToEnum(int typeNum) {
		ReimTypes type;
		if(typeNum == 1) {
			type = ReimTypes.Lodging;
			return type;
		} else if(typeNum == 2) {
			type = ReimTypes.Travel;
			return type;
		} else if(typeNum == 3) {
			type = ReimTypes.Food;
			return type;
		} else if(typeNum == 4) {
			type = ReimTypes.Other;
			return type;
		}
		return null;
	}
	
	public int enumToType(ReimTypes typeEnum) {
		int type;
		if(typeEnum == ReimTypes.Lodging) {
			type = 1;
			return type;
		} else if (typeEnum == ReimTypes.Travel) {
			type = 2;
			return type;
		} else if (typeEnum == ReimTypes.Food) {
			type = 3;
			return type;
		} else if (typeEnum == ReimTypes.Other) {
			type = 4;
			return type;
		}
		return 100;
	}
	
	public TreeMap<Integer, Reimbursement> grabCompleteReimbursements(int userID) {
		TreeMap<Integer, Reimbursement> reimbursements = rDAO.getReimbursementsFromUserId(userID);
		for(int i = 1; i <= reimbursements.size(); i++) {
			reimbursements.get(i).setStatus(statusToEnum(reimbursements.get(i).getStatusNum()));
			reimbursements.get(i).setType(typeToEnum(reimbursements.get(i).getTypeNum()));
		}
		return reimbursements;
	}
	
	public String createSubmissionTime() {
		Date dateObj = new Date();
		String dateFormat = "yyyy-MM-d    h:mm:ss a";
		SimpleDateFormat simpleDF = new SimpleDateFormat(dateFormat);
		return simpleDF.format(dateObj);
	}
	
	
	public boolean isValidReimbursement(Reimbursement reimbursement) {
		double amt = ((double)((int)(reimbursement.getAmount()*100)))/100;
		if(amt >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public TreeMap<Integer, Reimbursement> grabAllCompleteReimbursements() {
		TreeMap<Integer, Reimbursement> allReimbursements = rDAO.getAllReimbursements();
		for(int i = 1; i <= allReimbursements.size(); i++) {
			allReimbursements.get(i).setStatus(statusToEnum(allReimbursements.get(i).getStatusNum()));
			allReimbursements.get(i).setType(typeToEnum(allReimbursements.get(i).getTypeNum()));
		}
		return allReimbursements;
	}
	
	public boolean updateStatus(int reimID, int newStatus, int resolver) {
		Reimbursement reimbursement = rDAO.getReimbursementFromReimId(reimID);
		reimbursement.setStatusNum(newStatus);
		reimbursement.setTimeResolved(createSubmissionTime());
		reimbursement.setResolver(resolver);
		if(rDAO.updateReimbursementStatus(reimbursement)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getReceiptFromReimId(int reimId) {
		Reimbursement reimbursement = rDAO.getReimbursementFromReimId(reimId);
		byte[] receiptArray = reimbursement.getReceipt();
		String receipt64 = Base64.getEncoder().encodeToString(receiptArray);
		return receipt64;
	}
	
}