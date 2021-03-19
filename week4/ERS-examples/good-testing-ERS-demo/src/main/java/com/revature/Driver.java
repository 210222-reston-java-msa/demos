package com.revature;

import org.apache.commons.codec.digest.DigestUtils;

import com.revature.models.User;
import com.revature.repositories.ReimbursementsDAOImpl;
import com.revature.repositories.UsersDAOImpl;
import com.revature.services.ReimbursementLogic;

public class Driver {

	public static void main(String[] args) {
		UsersDAOImpl uDAO = new UsersDAOImpl();
		User test = uDAO.getUserByUserName("Employee");
		
		System.out.println(test.toString());
		
		String password = "Password1";
		password = DigestUtils.sha256Hex(password);
		System.out.println(password);

		ReimbursementLogic rLogic = new ReimbursementLogic();
		String time = rLogic.createSubmissionTime();
		System.out.println(time);
		
		ReimbursementsDAOImpl rDAO = new ReimbursementsDAOImpl();
		//System.out.println(rDAO.getReimbursementsFromUserId(2));
		//System.out.println(rLogic.grabAllCompleteReimbursements());
	}

}
