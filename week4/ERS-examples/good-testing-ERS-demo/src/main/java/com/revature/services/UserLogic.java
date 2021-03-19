package com.revature.services;

import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;

import com.revature.models.ReimStatus;
import com.revature.models.ReimTypes;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.repositories.ReimbursementsDAO;
import com.revature.repositories.ReimbursementsDAOImpl;
import com.revature.repositories.UsersDAO;
import com.revature.repositories.UsersDAOImpl;

public class UserLogic {

	
	public UserLogic() {
		
	}
	
	public UserLogic(UsersDAO uDAO) {
		this.uDAO = uDAO;
	}
	

	
	UsersDAO uDAO = new UsersDAOImpl();
	ReimbursementsDAO rDAO = new ReimbursementsDAOImpl();
	ReimbursementLogic rLogic = new ReimbursementLogic();

	



	// grab a whole user to work with --
	public User grabWholeUser(String userName, String password) {
		User user = uDAO.getUserByUserName(userName);
		if (user != null) {
			if (user.getUserPassword().equals(password)) {
				TreeMap<Integer, Reimbursement> reimbursements = rLogic.grabCompleteReimbursements(user.getUserId());
				user.setUserReimbursements(reimbursements);
				user.setRole(roleToEnum(user.getRoleNum()));
				return user;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	

	
	public UserRoles roleToEnum(int roleNum) {
		UserRoles role;
		if(roleNum == 1) {
			role = UserRoles.Employee;
			return role;
		} else if(roleNum == 2) {
			role = UserRoles.FinanceManager;
			return role;
		}
		return null;
	}
	
	
	public User login(String username, String password) {
		password = DigestUtils.sha256Hex(password);
		User user = grabWholeUser(username, password);
		return user;
	}
	
	public boolean createNewUser(User user) {
		if (uDAO.getUserByUserName(user.getUserName()) == null) {
			if (uDAO.createUser(user)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean userExists(String username) {
		User userCheck = uDAO.getUserByUserName(username);
		if(userCheck == null) {
			return false;
		} else {
			return true;
		}
	}
}
